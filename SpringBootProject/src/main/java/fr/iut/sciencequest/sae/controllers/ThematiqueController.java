package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.assemblers.IndiceModelAssembler;
import fr.iut.sciencequest.sae.assemblers.ThematiqueModelAssembler;
import fr.iut.sciencequest.sae.dto.thematique.ThematiqueDTO;
import fr.iut.sciencequest.sae.entities.Thematique;
import fr.iut.sciencequest.sae.services.interfaces.IThematiqueService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/thematiques")
public class ThematiqueController extends Controller {
    private final IThematiqueService thematiqueService;
    private final ThematiqueModelAssembler thematiqueModelAssembler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<Thematique> getAllThematiques() {
        return getSelfLinkCollectionModel(this.thematiqueService.findAll(), "getAllThematiques");
    }

    //TODO : gestion des erreurs remontées par @Valid
    //TODO : ajouter liens hateos
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ThematiqueDTO createThematique(@Valid @RequestBody Thematique thematique) {
        return thematiqueModelAssembler.toModel(this.thematiqueService.create(thematique));
    }

    @PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ThematiqueDTO updateThematique(@PathVariable("id") int id, @Valid @RequestBody Thematique thematique) {
        thematique.setId(id);
        return thematiqueModelAssembler.toModel(this.thematiqueService.update(thematique));
    }
}
