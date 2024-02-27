package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.entities.Thematique;
import fr.iut.sciencequest.sae.exceptions.DuplicatedEntity;
import fr.iut.sciencequest.sae.repositories.ThematiqueRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/thematiques")
public class ThematiqueController {

    private final ThematiqueRepository thematiqueRepository;

    public ThematiqueController(ThematiqueRepository thematiqueRepository) {
        this.thematiqueRepository = thematiqueRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Thematique> getAllThematiques() {
        return this.thematiqueRepository.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Thematique postThematique(@RequestBody Thematique thematique, HttpServletRequest request){
        if(this.thematiqueRepository.existsById(thematique.getId()))
            throw new DuplicatedEntity("Une thématique avec l'ID "+thematique.getId()+" existe déjà");
        try{
            thematique = this.thematiqueRepository.save(thematique);
            return thematique;
        } catch (DataIntegrityViolationException e) {
            throw new DuplicatedEntity("Une thématique avec le libelle : " + thematique.getLibelle() + " existe déjà");
        }
    }
}
