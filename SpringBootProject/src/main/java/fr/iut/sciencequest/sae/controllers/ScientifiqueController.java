package fr.iut.sciencequest.sae.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import fr.iut.sciencequest.sae.entities.Indice;
import fr.iut.sciencequest.sae.entities.Scientifique;
import fr.iut.sciencequest.sae.exceptions.DuplicatedEntity;
import fr.iut.sciencequest.sae.exceptions.IncorrectPageException;
import fr.iut.sciencequest.sae.exceptions.ScientifiqueNotFoundException;
import fr.iut.sciencequest.sae.repositories.IndiceRepository;
import fr.iut.sciencequest.sae.repositories.ScientifiqueRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/scientifiques")
public class ScientifiqueController {

    private static final int PAGE_SIZE = 1;

    private final ScientifiqueRepository scientifiqueRepository;
    private final IndiceRepository indiceRepository;

    public ScientifiqueController(ScientifiqueRepository scientifiqueRepository, IndiceRepository indiceRepository) {
        this.scientifiqueRepository = scientifiqueRepository;
        this.indiceRepository = indiceRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CollectionModel<EntityModel<Scientifique>> getAllScientists(@RequestParam(name = "page") Optional<Integer> page) {
        try {
            Pageable paging = PageRequest.of(page.orElse(0), PAGE_SIZE);
            Page<Scientifique> pagedResult = scientifiqueRepository.findAll(paging);

            List<EntityModel<Scientifique>> scientifiques = pagedResult.map(EntityModel::of).toList();

            Link selfLink = linkTo(methodOn(ScientifiqueController.class).getAllScientists(page)).withSelfRel().expand(page.map(Object::toString).orElse("0"));

            CollectionModel<EntityModel<Scientifique>> result = CollectionModel.of(scientifiques, selfLink);

            if (pagedResult.hasPrevious()) {
                Link prevLink = linkTo(methodOn(ScientifiqueController.class).getAllScientists(Optional.of(pagedResult.previousPageable().getPageNumber()))).withRel("previous");
                result.add(prevLink.expand(pagedResult.previousPageable().getPageNumber()));
            }

            if (pagedResult.hasNext()) {
                Link nextLink = linkTo(methodOn(ScientifiqueController.class).getAllScientists(Optional.of(pagedResult.nextPageable().getPageNumber()))).withRel("next");
                result.add(nextLink.expand(pagedResult.nextPageable().getPageNumber()));
            }

            return result;
        } catch (IllegalArgumentException e) {
            throw new IncorrectPageException("numéro de page incorrect");
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public EntityModel<Optional<Scientifique>> getScientistById(@PathVariable int id) {
        Optional<Scientifique> scientifiqueOptional = this.scientifiqueRepository.findById(id);
        Scientifique scientifique = scientifiqueOptional.orElseThrow(() -> new ScientifiqueNotFoundException("Scientifique non trouvé avec l'ID : " + id));

        Link selfLink = linkTo(methodOn(ScientifiqueController.class).getScientistById(id)).withSelfRel();
        return EntityModel.of(Optional.ofNullable(scientifique), selfLink);
    }

    @GetMapping(value="/{id}/indices", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CollectionModel<EntityModel<Indice>> getScientistHints(@PathVariable int id, @RequestParam(name = "page") Optional<Integer> page) {
        try {
            Pageable paging = PageRequest.of(page.orElse(0), PAGE_SIZE);
            Page<Indice> pagedResult = indiceRepository.findAll(paging);

            List<EntityModel<Indice>> indices = pagedResult.map(EntityModel::of).toList();

            Link selfLink = linkTo(methodOn(ScientifiqueController.class).getScientistHints(id, page)).withSelfRel().expand(page.map(Object::toString).orElse("0"));

            CollectionModel<EntityModel<Indice>> result = CollectionModel.of(indices, selfLink);

            if (pagedResult.hasPrevious()) {
                Link prevLink = linkTo(methodOn(ScientifiqueController.class).getScientistHints(id, Optional.of(pagedResult.previousPageable().getPageNumber()))).withRel("previous");
                result.add(prevLink.expand(pagedResult.previousPageable().getPageNumber()));
            }

            if (pagedResult.hasNext()) {
                Link nextLink = linkTo(methodOn(ScientifiqueController.class).getScientistHints(id, Optional.of(pagedResult.nextPageable().getPageNumber()))).withRel("next");
                result.add(nextLink.expand(pagedResult.nextPageable().getPageNumber()));
            }

            return result;
        } catch (IllegalArgumentException e) {
            throw new IncorrectPageException("numéro de page incorrect");
        }
    }

    @PostMapping(value="/{id}/indices", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Indice postIndice(@RequestBody Indice indice){
        try{

            return this.indiceRepository.save(indice);
        } catch (DataIntegrityViolationException e){
            throw new DuplicatedEntity(e.getMessage());
        }
    }
}
