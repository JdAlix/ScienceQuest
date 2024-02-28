package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.entities.IidAndLibelleOnly;
import fr.iut.sciencequest.sae.entities.Indice;
import fr.iut.sciencequest.sae.entities.Scientifique;
import fr.iut.sciencequest.sae.exceptions.DuplicatedFieldException;
import fr.iut.sciencequest.sae.exceptions.IncorrectPageException;
import fr.iut.sciencequest.sae.repositories.IndiceRepository;
import fr.iut.sciencequest.sae.services.interfaces.IScientifiqueService;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/v1/scientifiques")
public class ScientifiqueController {

    private final IScientifiqueService scientifiqueService;
    private final IndiceRepository indiceRepository;

    private final int PAGE_SIZE = 1;

    public ScientifiqueController(IScientifiqueService scientifiqueService, IndiceRepository indiceRepository) {
        this.scientifiqueService = scientifiqueService;
        this.indiceRepository = indiceRepository;
    }

    private <T> CollectionModel<EntityModel<T>> getPageableCollectionModel(Page<T> pagedResult, Integer page, Method method, Object... args) {
        List<EntityModel<T>> entities = pagedResult.map(EntityModel::of).toList();

        List<Object> selfObj = new ArrayList<>(List.of(args)); selfObj.add(page);
        Link selfLink = linkTo(ScientifiqueController.class, method, selfObj.toArray()).withSelfRel().expand(page);

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
        return this.scientifiqueService.findAll(page.orElse(0));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public EntityModel<Optional<Scientifique>> getScientistById(@PathVariable int id) {
        return this.scientifiqueService.findById(id);
    }

    @GetMapping(value="/{id}/indices", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public /*CollectionModel<EntityModel<Indice>>*/ Iterable<IidAndLibelleOnly> getScientistHints(@PathVariable int id, @RequestParam(name = "page") Optional<Integer> page) {
        return this.indiceRepository.findByScientifiqueId(id);

        /*try {
            Pageable paging = PageRequest.of(page.orElse(0), PAGE_SIZE);
            Page<Indice> pagedResult = indiceRepository.findAll(paging);

            return this.getPageableCollectionModel(pagedResult, page.orElse(0), ScientifiqueController.class.getMethod("getScientistHints", int.class, Optional.class), id);
        } catch (IllegalArgumentException e) {
            throw new IncorrectPageException("numéro de page incorrect");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }*/
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
