package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.entities.Thematique;
import fr.iut.sciencequest.sae.repositories.ThematiqueRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/thematiques")
public class ThematiqueController {
    private final ThematiqueRepository thematiqueRepository;

    public ThematiqueController(ThematiqueRepository thematiqueRepository) {
        this.thematiqueRepository = thematiqueRepository;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<Thematique> getAllThematiques() {
        return this.thematiqueRepository.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    
    @ResponseBody
    public Thematique postThematique(@)
}
