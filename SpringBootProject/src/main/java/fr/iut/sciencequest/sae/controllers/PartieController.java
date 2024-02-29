package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.assemblers.PartieModelAssembler;
import fr.iut.sciencequest.sae.assemblers.QuestionModelAssembler;
import fr.iut.sciencequest.sae.dto.PartieDTO;
import fr.iut.sciencequest.sae.entities.Partie;
import fr.iut.sciencequest.sae.entities.Question;
import fr.iut.sciencequest.sae.exceptions.DuplicatedFieldException;
import fr.iut.sciencequest.sae.exceptions.notFound.PartieNotFoundException;
import fr.iut.sciencequest.sae.repositories.PartieRepository;
import fr.iut.sciencequest.sae.services.PartieService;
import fr.iut.sciencequest.sae.services.QuestionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/partie")
public class PartieController extends Controller {
    private final PartieModelAssembler partieModelAssembler;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private final PagedResourcesAssembler<Partie> pagedResourcesAssembler;
    private final PartieService partieService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PartieDTO getPartie(@PathVariable int id) {
        Partie partie = this.partieService.findById(id);
        return partieModelAssembler.toModel(partie);
    }

    /*
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Partie createPartie(@RequestBody Partie partie) {
        try {
            return this.partieRepository.save(partie);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicatedFieldException("ERREUR : il existe déjà une partie : " + partie.getId() + " en base");
        }
    }*/


}
