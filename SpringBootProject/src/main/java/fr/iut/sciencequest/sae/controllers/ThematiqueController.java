package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.entities.Thematique;
import fr.iut.sciencequest.sae.services.ThematiqueService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/thematiques")
public class ThematiqueController {

    private final ThematiqueService thematiqueService;

    public ThematiqueController(ThematiqueService thematiqueService) {
        this.thematiqueService = thematiqueService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Thematique> getAllThematiques() {
        return this.thematiqueService.findAll();
    }


    //TODO : gestion des erreurs remontées par @Valid
    //TODO : ajouter liens hateos
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Thematique createThematique(@Valid @RequestBody Thematique thematique){
        return this.thematiqueService.create(thematique);
    }

    @PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Thematique updateThematique(@PathVariable("id") int id, @Valid @RequestBody Thematique thematique){
        thematique.setId(id);
        return this.thematiqueService.update(thematique);
    }
}
