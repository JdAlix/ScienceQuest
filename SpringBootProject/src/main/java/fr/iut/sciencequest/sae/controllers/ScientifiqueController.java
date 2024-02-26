package fr.iut.sciencequest.sae.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import fr.iut.sciencequest.sae.entities.Scientifique;
import fr.iut.sciencequest.sae.exceptions.IncorrectPageException;
import fr.iut.sciencequest.sae.exceptions.ScientifiqueNotFoundException;
import fr.iut.sciencequest.sae.repositories.ScientifiqueRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller()
@RequestMapping("/api/v1/scientifiques")
public class ScientifiqueController {

    private static final int PAGE_SIZE = 10;
    private final ScientifiqueRepository scientifiqueRepository;

    public ScientifiqueController(ScientifiqueRepository scientifiqueRepository) {
        this.scientifiqueRepository = scientifiqueRepository;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CollectionModel<EntityModel<Scientifique>> getAllScientists(@RequestParam(name = "page") Optional<Integer> page, HttpServletRequest request) {
        try {
            Pageable paging = PageRequest.of(page.orElse(0), PAGE_SIZE);
            Page<Scientifique> pagedResult = scientifiqueRepository.findAll(paging);

            List<EntityModel<Scientifique>> scientifiques = pagedResult.map(EntityModel::of).toList();

            Link selfLink = linkTo(methodOn(ScientifiqueController.class).getAllScientists(page, request)).withSelfRel().expand(page.map(Object::toString).orElse("0"));

            CollectionModel<EntityModel<Scientifique>> result = CollectionModel.of(scientifiques, selfLink);

            if (pagedResult.hasPrevious()) {
                Link prevLink = linkTo(methodOn(QuestionController.class).getAllQuestions(Optional.of(pagedResult.previousPageable().getPageNumber()), request)).withRel("previous");
                result.add(prevLink.expand(pagedResult.previousPageable().getPageNumber()));
            }

            if (pagedResult.hasNext()) {
                Link nextLink = linkTo(methodOn(QuestionController.class).getAllQuestions(Optional.of(pagedResult.nextPageable().getPageNumber()), request)).withRel("next");
                result.add(nextLink.expand(pagedResult.nextPageable().getPageNumber()));
            }

            return result;
        } catch (IllegalArgumentException e) {
            throw new IncorrectPageException(request.getRequestURI(), "numéro de page incorrect");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public EntityModel<Optional<Scientifique>> getScientistById(@PathVariable int id, HttpServletRequest request) {
        Optional<Scientifique> scientifiqueOptional = this.scientifiqueRepository.findById(id);
        Scientifique scientifique = scientifiqueOptional.orElseThrow(() -> new ScientifiqueNotFoundException(request.getRequestURI(), "Scientifique non trouvé avec l'ID : " + id));

        Link selfLink = linkTo(methodOn(ScientifiqueController.class).getScientistById(id, request)).withSelfRel();
        return EntityModel.of(Optional.ofNullable(scientifique), selfLink);
    }
}
