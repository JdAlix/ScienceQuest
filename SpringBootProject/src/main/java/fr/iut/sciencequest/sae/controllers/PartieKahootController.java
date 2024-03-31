package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.controllers.request.PartieAddJoueurRequest;
import fr.iut.sciencequest.sae.controllers.request.PartieRequest;
import fr.iut.sciencequest.sae.dto.partieKahoot.PartieKahootDTO;
import fr.iut.sciencequest.sae.dto.partieKahoot.PartieKahootStatusDTO;
import fr.iut.sciencequest.sae.entities.*;
import fr.iut.sciencequest.sae.exceptions.partie.PartyAlreadyStartedException;
import fr.iut.sciencequest.sae.repositories.ScorePartieKahootJoueurRepository;
import fr.iut.sciencequest.sae.services.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/partie/kahoot")
public class PartieKahootController {
    private final ScorePartieKahootJoueurRepository scorePartieKahootJoueurRepository;
    private final PartieKahootService partieKahootService;
    private final JoueurService joueurService;
    private final QuestionService questionService;
    private final ThematiqueService thematiqueService;
    private final DifficulteService difficulteService;
    private final ModelMapper modelMapper;

    @RequestMapping(value = "/{codeInvitation}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PartieKahootDTO getPartie(@PathVariable String codeInvitation) {
        return this.modelMapper.map(this.partieKahootService.getPartieKahootByIdOrCodeInvitation(codeInvitation), PartieKahootDTO.class);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PartieKahootDTO createPartie(@RequestBody @Valid PartieRequest request) {
        PartieKahoot partie = new PartieKahoot();
        Joueur joueur = this.joueurService.findById(request.getIdJoueur());
        partie.setJoueurs(List.of(joueur));
        partie.setThematiques(new ArrayList<>());
        for(int idThematique: request.getThematiques()){
            partie.getThematiques().add(this.thematiqueService.findById(idThematique));
        }

        partie.setDifficulte(this.difficulteService.findById(request.getIdDifficulte()));

        partie.setQuestions(this.questionService.getRandomQuestions(1, partie.getThematiques(), partie.getDifficulte()));

        partie =  this.partieKahootService.create(partie);
        ScorePartieKahootJoueur score = new ScorePartieKahootJoueur();
        score.setId(new ScorePartieKahootJoueurKey(joueur.getId(), partie.getId()));
        score.setJoueur(joueur);
        score.setPartie(partie);
        this.scorePartieKahootJoueurRepository.save(score);
        return this.modelMapper.map(partie, PartieKahootDTO.class);
    }

    @PutMapping(value= "/{codeInvitation}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public PartieKahootDTO addPlayerToPartie(@PathVariable String codeInvitation, @RequestBody @Valid PartieAddJoueurRequest request){
        Joueur joueur = this.joueurService.findById(request.getIdJoueur());
        PartieKahoot partie = this.partieKahootService.getPartieKahootByIdOrCodeInvitation(codeInvitation);
        if(!partie.getStatus().equals(Status.Pending)) throw new PartyAlreadyStartedException();
        if(!partie.getJoueurs().contains(joueur)){
            ScorePartieKahootJoueur score = new ScorePartieKahootJoueur();
            score.setPartie(partie);
            score.setJoueur(joueur);
            partie.getScores().add(score);
            partie.getJoueurs().add(joueur);
        }
        return this.modelMapper.map(this.partieKahootService.update(partie), PartieKahootDTO.class);
    }

    @PostMapping(value = "/{codeInvitation}/demarrer", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public PartieKahootStatusDTO demarrerPartie(@PathVariable String codeInvitation){
        PartieKahoot partieKahoot = this.partieKahootService.getPartieKahootByIdOrCodeInvitation(codeInvitation);
        if(partieKahoot.getStatus() == Status.Started){
            throw new PartyAlreadyStartedException();
        }
        partieKahoot.setStatus(Status.Started);
        partieKahoot = this.partieKahootService.update(partieKahoot);
        return this.modelMapper.map(partieKahoot, PartieKahootStatusDTO.class);
    }

}
