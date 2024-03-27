package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.assemblers.PartieModelAssembler;
import fr.iut.sciencequest.sae.controllers.request.PartieAddJoueurRequest;
import fr.iut.sciencequest.sae.controllers.request.PartieRequest;
import fr.iut.sciencequest.sae.dto.partie.PartieDTO;
import fr.iut.sciencequest.sae.entities.Joueur;
import fr.iut.sciencequest.sae.entities.Partie;
import fr.iut.sciencequest.sae.entities.Thematique;
import fr.iut.sciencequest.sae.exceptions.notFound.PartieNotFoundException;
import fr.iut.sciencequest.sae.exceptions.partie.PartyAlreadyStartedException;
import fr.iut.sciencequest.sae.services.JeuService;
import fr.iut.sciencequest.sae.services.JoueurService;
import fr.iut.sciencequest.sae.services.PartieService;
import fr.iut.sciencequest.sae.services.ThematiqueService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    private final ThematiqueService thematiqueService;
    private final ModelMapper modelMapper;

    @RequestMapping(value = "/{codeInvitation}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PartieDTO getPartie(@PathVariable String codeInvitation) {
        return partieModelAssembler.toModel(this.partieService.getPartieByIdOrCodeInvitation(codeInvitation));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PartieDTO createPartie(@RequestBody @Valid PartieRequest request) {
        Partie partie = new Partie();
        partie.setJeu(this.jeuService.findById(request.getIdJeu()));
        partie.setJoueurs(List.of(this.joueurService.findById(request.getIdJoueur())));

        partie.setThematiques(new ArrayList<>());
        for(int idThematique: request.getThematiques()){
            partie.getThematiques().add(this.thematiqueService.findById(idThematique));
        }
        partie =  this.partieService.create(partie);
        return this.modelMapper.map(partie, PartieDTO.class);
    }

    @PutMapping(value= "/{codeInvitation}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public PartieDTO addPlayerToPartie(@PathVariable String codeInvitation, @RequestBody @Valid PartieAddJoueurRequest request){
        Joueur joueur = this.joueurService.findById(request.getIdJoueur());
        Partie partie = this.partieService.getPartieByIdOrCodeInvitation(codeInvitation);
        if(!partie.getStatus().equals("pending")) throw new PartyAlreadyStartedException();
        if(!partie.getJoueurs().contains(joueur)){
            partie.getJoueurs().add(joueur);
        }
        return this.modelMapper.map(this.partieService.update(partie), PartieDTO.class);
    }

}
