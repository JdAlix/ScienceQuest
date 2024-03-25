package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.assemblers.PartieModelAssembler;
import fr.iut.sciencequest.sae.controllers.request.PartieRequest;
import fr.iut.sciencequest.sae.dto.partie.PartieDTO;
import fr.iut.sciencequest.sae.entities.Partie;
import fr.iut.sciencequest.sae.services.PartieService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/partie")
public class PartieController {
    private final PartieModelAssembler partieModelAssembler;
    private final PartieService partieService;
    private final ModelMapper modelMapper;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PartieDTO getPartie(@PathVariable int id) {
        Partie partie = this.partieService.findById(id);
        return partieModelAssembler.toModel(partie);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Partie createPartie(@RequestBody PartieRequest request) {
        return this.partieService.create(request.getIdJeu(), request.getPseudo(), request.getThematiques(), request.getIdDifficulte());
    }

}
