package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.entities.Scientifique;
import fr.iut.sciencequest.sae.entities.indice.IIndiceidAndLibelleOnlyProjection;
import fr.iut.sciencequest.sae.entities.indice.Indice;
import fr.iut.sciencequest.sae.entities.indice.IValidateOnlyLibelle;
import fr.iut.sciencequest.sae.exceptions.IncorrectPageException;
import fr.iut.sciencequest.sae.services.IndiceService;
import fr.iut.sciencequest.sae.services.interfaces.IScientifiqueService;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/scientifiques")
public class ScientifiqueController {

    private final IScientifiqueService scientifiqueService;
    private final IndiceService indiceService;

    private final int PAGE_SIZE = 1;


    public ScientifiqueController(IScientifiqueService scientifiqueService, IndiceService indiceService) {
        this.scientifiqueService = scientifiqueService;
        this.indiceService = indiceService;
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
        try {
            Page<Scientifique> pagedResult = this.scientifiqueService.findAll(page.orElse(0));
            return getPageableCollectionModel(pagedResult, page.orElse(0), ScientifiqueController.class.getMethod("getAllScientists", Optional.class));
        } catch (IllegalArgumentException e) {
            throw new IncorrectPageException("numéro de page incorrect");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public EntityModel<Scientifique> getScientistById(@PathVariable int id) {
        Link selfLink = linkTo(methodOn(ScientifiqueController.class).getScientistById(id)).withSelfRel();
        return EntityModel.of(this.scientifiqueService.findById(id), selfLink);
    }

    @GetMapping(value="/{id}/indices", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CollectionModel<IIndiceidAndLibelleOnlyProjection> getScientistHints(@PathVariable int id) {
        Link selfLink = linkTo(methodOn(ScientifiqueController.class).getScientistHints(id)).withSelfRel();
        return CollectionModel.of(this.scientifiqueService.getLinkedIndicesByScientifiqueId(id), selfLink);
    }

    @PostMapping(value="/{id}/indices", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Indice postIndice(@PathVariable int id, @Validated(IValidateOnlyLibelle.class) @RequestBody Indice indice){
        indice.setScientifique(this.scientifiqueService.findById(id));
        return this.indiceService.create(indice);
    }

}
