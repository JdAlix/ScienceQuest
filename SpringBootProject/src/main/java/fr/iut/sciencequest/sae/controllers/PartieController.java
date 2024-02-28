package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.entities.Partie;
import fr.iut.sciencequest.sae.exceptions.DuplicatedFieldException;
import fr.iut.sciencequest.sae.exceptions.PartieNotFoundException;
import fr.iut.sciencequest.sae.repositories.PartieRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/partie")
public class PartieController {

    private static final int PAGE_SIZE = 5;
    private final PartieRepository partieRepository;

    public PartieController(PartieRepository partieRepository) {
        this.partieRepository = partieRepository;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<Optional<Partie>> getPartie(@PathVariable int id, HttpServletRequest request) {
        Optional<Partie> partieOptional = this.partieRepository.findById(id);
        Partie partie = partieOptional.orElseThrow(() ->
                new PartieNotFoundException("Partie introuvable avec l'ID : " + id)
        );

        Link selfLink = linkTo(methodOn(PartieController.class).getPartie(id,request)).withSelfRel();
        return EntityModel.of(Optional.ofNullable(partie), selfLink);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Partie createPartie(@RequestBody Partie partie) {
        try {
            return this.partieRepository.save(partie);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicatedFieldException("ERREUR : il existe déjà une partie : " + partie.getId() + " en base");
        }
    }


}
