package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.ApplicationConfig;
import fr.iut.sciencequest.sae.assemblers.ScientifiqueModelAssembler;
import fr.iut.sciencequest.sae.dto.scientifique.ScientifiqueDTO;
import fr.iut.sciencequest.sae.entities.scientifique.Scientifique;
import fr.iut.sciencequest.sae.entities.indice.IIndiceidAndLibelleAndScientifiqueIdOnlyProjection;
import fr.iut.sciencequest.sae.entities.indice.Indice;
import fr.iut.sciencequest.sae.entities.indice.IValidateOnlyLibelle;
import fr.iut.sciencequest.sae.exceptions.IncorrectPageException;
import fr.iut.sciencequest.sae.services.IndiceService;
import fr.iut.sciencequest.sae.services.interfaces.IScientifiqueService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/scientifiques")
public class ScientifiqueController extends Controller {

    private final IScientifiqueService scientifiqueService;
    private final ScientifiqueModelAssembler scientifiqueModelAssembler;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private final PagedResourcesAssembler<Scientifique> pagedResourcesAssembler;
    private final IndiceService indiceService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedModel<ScientifiqueDTO> getAllScientists(@PageableDefault(size = ApplicationConfig.DEFAULT_PAGEABLE_SIZE) Pageable p) {
        try {
            return pagedResourcesAssembler.toModel(this.scientifiqueService.findAll(p), scientifiqueModelAssembler);
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
