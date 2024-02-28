package fr.iut.sciencequest.sae.controllers;


import fr.iut.sciencequest.sae.entities.Difficulte;
import fr.iut.sciencequest.sae.services.DifficulteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/difficultes")
public class DifficulteController {

    public final DifficulteService difficulteService;

    public DifficulteController(DifficulteService difficulteService){
        this.difficulteService = difficulteService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Difficulte> getAllDifficultes(){
        return this.difficulteService.findAll();
    }
}
