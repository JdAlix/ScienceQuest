package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.assemblers.PartieModelAssembler;
import fr.iut.sciencequest.sae.controllers.request.PartieRequest;
import fr.iut.sciencequest.sae.dto.partie.PartieDTO;
import fr.iut.sciencequest.sae.entities.Joueur;
import fr.iut.sciencequest.sae.entities.Partie;
import fr.iut.sciencequest.sae.exceptions.notFound.PartieNotFoundException;
import fr.iut.sciencequest.sae.services.JeuService;
import fr.iut.sciencequest.sae.services.JoueurService;
import fr.iut.sciencequest.sae.services.PartieService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/partie")
public class PartieController {
    private final PartieModelAssembler partieModelAssembler;
    private final PartieService partieService;
    private final JoueurService joueurService;
    private final JeuService jeuService;
    private final ModelMapper modelMapper;

    @RequestMapping(value = "/{codeInvitation}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PartieDTO getPartie(@PathVariable String codeInvitation) {
        //try to get invitation from codeInvitation, if not : try with id
        Partie partie;
        try{
            partie = this.partieService.getPartieByCodeInvitation(codeInvitation);
        }catch (PartieNotFoundException exceptionNotFoundByCodeInvitation){
            try{
                partie = this.partieService.findById(Integer.parseInt(codeInvitation));
            }catch (PartieNotFoundException | NumberFormatException e2){
                throw exceptionNotFoundByCodeInvitation;
            }
        }
        return partieModelAssembler.toModel(partie);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PartieDTO createPartie(@RequestBody @Valid PartieRequest request) {
        Partie partie = new Partie();
        partie.setJeu(this.jeuService.findById(request.getIdJeu()));
        partie.setJoueurs(List.of(this.joueurService.findById(request.getIdJoueur())));
        partie =  this.partieService.create(partie);
        return this.modelMapper.map(partie, PartieDTO.class);
    }

}
