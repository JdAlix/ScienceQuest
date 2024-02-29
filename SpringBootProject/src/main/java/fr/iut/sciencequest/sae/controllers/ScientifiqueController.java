package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.entities.scientifique.Scientifique;
import fr.iut.sciencequest.sae.entities.indice.IIndiceidAndLibelleAndScientifiqueIdOnlyProjection;
import fr.iut.sciencequest.sae.entities.indice.Indice;
import fr.iut.sciencequest.sae.entities.indice.IValidateOnlyLibelle;
import fr.iut.sciencequest.sae.exceptions.IncorrectPageException;
import fr.iut.sciencequest.sae.services.IndiceService;
import fr.iut.sciencequest.sae.services.interfaces.IScientifiqueService;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/v1/scientifiques")
public class ScientifiqueController extends Controller {

    private final IScientifiqueService scientifiqueService;
    private final IndiceService indiceService;

    public ScientifiqueController(IScientifiqueService scientifiqueService, IndiceService indiceService) {
        this.scientifiqueService = scientifiqueService;
        this.indiceService = indiceService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<EntityModel<Scientifique>> getAllScientists(@RequestParam(name = "page") Optional<Integer> page) {
        try {
            return getPageableCollectionModel(this.scientifiqueService.findAll(page.orElse(0)), page.orElse(0),"getAllScientists");
        } catch (IllegalArgumentException e) {
            throw new IncorrectPageException("numéro de page incorrect");
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<Scientifique> getScientistById(@PathVariable Integer id) {
        return getSelfLinkEntityModel(this.scientifiqueService.findById(id), "getScientistById", id);
    }

    @GetMapping(value="/{id}/indices", produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<IIndiceidAndLibelleAndScientifiqueIdOnlyProjection> getScientistHints(@PathVariable Integer id) {
        return getSelfLinkCollectionModel(this.scientifiqueService.getLinkedIndicesByScientifiqueId(id), "getScientistHints", id);
    }

    @PostMapping(value="/{id}/indices", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public IIndiceidAndLibelleAndScientifiqueIdOnlyProjection postIndice(@PathVariable Integer id, @Validated(IValidateOnlyLibelle.class) @RequestBody Indice indice){
        indice.setScientifique(this.scientifiqueService.findById(id));
        indice = this.indiceService.create(indice);
        return indice.toProjection(IIndiceidAndLibelleAndScientifiqueIdOnlyProjection.class);
    }
}
