package fr.iut.sciencequest.sae.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import fr.iut.sciencequest.sae.entities.Indice;
import fr.iut.sciencequest.sae.entities.Scientifique;
import fr.iut.sciencequest.sae.exceptions.DuplicatedFieldException;
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

import java.lang.reflect.Method;
import java.util.ArrayList;
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

    private <T> CollectionModel<EntityModel<T>> getPageableCollectionModel(Page<T> pagedResult, Optional<Integer> page, Method method, Object... args) {
        List<EntityModel<T>> entities = pagedResult.map(EntityModel::of).toList();

        List<Object> selfObj = new ArrayList<>(List.of(args)); selfObj.add(page.orElse(0));
        Link selfLink = linkTo(ScientifiqueController.class, method, selfObj.toArray()).withSelfRel().expand(page.orElse(0));

        CollectionModel<EntityModel<T>> result = CollectionModel.of(entities, selfLink);

        if (pagedResult.hasPrevious()) {
            List<Object> previousObj = new ArrayList<>(List.of(args)); previousObj.add(pagedResult.previousPageable().getPageNumber());
            result.add(linkTo(ScientifiqueController.class, method, previousObj.toArray()).withRel("previous"));
        }

        if (pagedResult.hasNext()) {
            List<Object> nextObj = new ArrayList<>(List.of(args)); nextObj.add(pagedResult.nextPageable().getPageNumber());
            result.add(linkTo(ScientifiqueController.class, method, nextObj.toArray()).withRel("next"));
        }

        return result;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CollectionModel<EntityModel<Scientifique>> getAllScientists(@RequestParam(name = "page") Optional<Integer> page) {
        try {
            Pageable paging = PageRequest.of(page.orElse(0), PAGE_SIZE);
            Page<Scientifique> pagedResult = scientifiqueRepository.findAll(paging);

            return getPageableCollectionModel(pagedResult, page, ScientifiqueController.class.getMethod("getAllScientists", Optional.class));
        } catch (IllegalArgumentException e) {
            throw new IncorrectPageException("numéro de page incorrect");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
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

            return getPageableCollectionModel(pagedResult, page, ScientifiqueController.class.getMethod("getScientistHints", int.class, Optional.class), id);
        } catch (IllegalArgumentException e) {
            throw new IncorrectPageException("numéro de page incorrect");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value="/{id}/indices", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Indice postIndice(@RequestBody Indice indice){
        try{

            return this.indiceRepository.save(indice);
        } catch (DataIntegrityViolationException e){
            throw new DuplicatedFieldException(e.getMessage());
        }
    }
}
