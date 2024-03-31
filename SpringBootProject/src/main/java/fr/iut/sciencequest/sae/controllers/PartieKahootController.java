package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.controllers.request.PartieAddJoueurRequest;
import fr.iut.sciencequest.sae.controllers.request.PartieRequest;
import fr.iut.sciencequest.sae.dto.partieKahoot.PartieKahootDTO;
import fr.iut.sciencequest.sae.dto.partieKahoot.PartieKahootQuestionDTO;
import fr.iut.sciencequest.sae.dto.partieKahoot.PartieKahootStatusDTO;
import fr.iut.sciencequest.sae.entities.*;
import fr.iut.sciencequest.sae.exceptions.partie.PartyAlreadyStartedException;
import fr.iut.sciencequest.sae.exceptions.partie.PartyNotStartedException;
import fr.iut.sciencequest.sae.repositories.QuestionPartieKahootRepository;
import fr.iut.sciencequest.sae.repositories.ScorePartieKahootJoueurRepository;
import fr.iut.sciencequest.sae.services.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/partie/kahoot")
public class PartieKahootController {
    private static final int NOMBRE_QUESTION = 2;

    private final QuestionPartieKahootRepository questionPartieKahootRepository;
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
        partie =  this.partieKahootService.create(partie);

        //setup score
        ScorePartieKahootJoueur score = new ScorePartieKahootJoueur();
        score.setId(new ScorePartieKahootJoueurKey(joueur.getId(), partie.getId()));
        score.setJoueur(joueur);
        score.setPartie(partie);
        this.scorePartieKahootJoueurRepository.save(score);

        //setup question
        List<Question> questions = this.questionService.getRandomQuestions(NOMBRE_QUESTION, partie.getThematiques(), partie.getDifficulte());
        for(Question question: questions){
            QuestionPartieKahoot questionPartie = new QuestionPartieKahoot();
            questionPartie.setId(new QuestionPartieKahootKey(question.getId(), partie.getId()));
            questionPartie.setQuestion(question);
            questionPartie.setPartie(partie);
            this.questionPartieKahootRepository.save(questionPartie);
        }

        partie = this.partieKahootService.findById(partie.getId());

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
            score.setId(new ScorePartieKahootJoueurKey(joueur.getId(), partie.getId()));
            score.setJoueur(joueur);
            score.setPartie(partie);
            partie.getScores().add(score);
            partie.getJoueurs().add(joueur);
            this.scorePartieKahootJoueurRepository.save(score);
        }
        return this.modelMapper.map(this.partieKahootService.update(partie), PartieKahootDTO.class);
    }

    @PostMapping(value = "/{codeInvitation}/demarrer", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public PartieKahootStatusDTO demarrerPartie(@PathVariable String codeInvitation){
        PartieKahoot partieKahoot = this.partieKahootService.getPartieKahootByIdOrCodeInvitation(codeInvitation);
        Calendar tempsLimiteReponse = Calendar.getInstance();
        tempsLimiteReponse.setTime(new Date());
        tempsLimiteReponse.add(Calendar.MINUTE, 1);

        if(partieKahoot.getStatus() == Status.Started){
            throw new PartyAlreadyStartedException();
        }
        partieKahoot.setStatus(Status.Started);
        partieKahoot.setQuestionActuel(partieKahoot.getQuestions().getFirst().getQuestion());
        partieKahoot.setTempsLimiteReponse(tempsLimiteReponse);
        partieKahoot = this.partieKahootService.update(partieKahoot);
        return this.modelMapper.map(partieKahoot, PartieKahootStatusDTO.class);
    }

    @GetMapping(value = "/{codeInvitation}/question", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public PartieKahootQuestionDTO getQuestionActuel(@PathVariable String codeInvitation){
        PartieKahoot partieKahoot = this.partieKahootService.getPartieKahootByIdOrCodeInvitation(codeInvitation);
        if(partieKahoot.getStatus() != Status.Started){
            throw new PartyNotStartedException();
        }
        return this.modelMapper.map(partieKahoot, PartieKahootQuestionDTO.class);
    }

}
