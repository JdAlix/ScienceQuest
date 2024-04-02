package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.controllers.request.PartieAddJoueurRequest;
import fr.iut.sciencequest.sae.controllers.request.PartieReponse;
import fr.iut.sciencequest.sae.controllers.request.PartieRequest;
import fr.iut.sciencequest.sae.dto.partieKahoot.PartieKahootDTO;
import fr.iut.sciencequest.sae.dto.partieKahoot.PartieKahootQuestionDTO;
import fr.iut.sciencequest.sae.dto.partieKahoot.PartieKahootStatusDTO;
import fr.iut.sciencequest.sae.dto.reponse.ReponseValideDTO;
import fr.iut.sciencequest.sae.entities.*;
import fr.iut.sciencequest.sae.exceptions.notFound.ScorePartieKahootJoueurNotFound;
import fr.iut.sciencequest.sae.exceptions.partie.*;
import fr.iut.sciencequest.sae.repositories.QuestionPartieKahootRepository;
import fr.iut.sciencequest.sae.repositories.ReponsePartieKahootRepository;
import fr.iut.sciencequest.sae.repositories.ScorePartieKahootJoueurRepository;
import fr.iut.sciencequest.sae.services.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    private final ReponseService reponseService;
    private final ThematiqueService thematiqueService;
    private final DifficulteService difficulteService;
    private final ModelMapper modelMapper;
    private final ReponsePartieKahootRepository reponsePartieKahootRepository;

    @RequestMapping(value = "/{codeInvitation}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PartieKahootDTO getPartie(@PathVariable String codeInvitation) {
        PartieKahoot partieKahoot = this.partieKahootService.getPartieKahootByIdOrCodeInvitation(codeInvitation);
        partieKahoot = this.partieKahootService.maintenirAJourQuestionActuel(partieKahoot);
        return this.modelMapper.map(partieKahoot, PartieKahootDTO.class);
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

    @PostMapping(value= "/{codeInvitation}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public PartieKahootDTO addPlayerToPartie(@PathVariable String codeInvitation, @RequestBody @Valid PartieAddJoueurRequest request){
        Joueur joueur = this.joueurService.findById(request.getIdJoueur());
        PartieKahoot partie = this.partieKahootService.getPartieKahootByIdOrCodeInvitation(codeInvitation);
        if(!partie.getStatus().equals(Status.Pending)) throw new PartyAlreadyStartedException();
        if(!partie.getJoueurs().stream().anyMatch(j -> j.getId() == joueur.getId())){
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

        if(partieKahoot.getStatus() == Status.Started || partieKahoot.getStatus() == Status.DisplayingScore){
            throw new PartyAlreadyStartedException();
        }
        partieKahoot.setStatus(Status.Started);

        partieKahoot = this.partieKahootService.questionSuivante(partieKahoot);
        return this.modelMapper.map(partieKahoot, PartieKahootStatusDTO.class);
    }

    @GetMapping(value = "/{codeInvitation}/status", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public PartieKahootStatusDTO getStatusPartie(@PathVariable String codeInvitation){
        PartieKahoot partieKahoot = this.partieKahootService.getPartieKahootByIdOrCodeInvitation(codeInvitation);
        partieKahoot = this.partieKahootService.maintenirAJourQuestionActuel(partieKahoot);
        return this.modelMapper.map(partieKahoot, PartieKahootStatusDTO.class);
    }

    @GetMapping(value = "/{codeInvitation}/question", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public PartieKahootQuestionDTO getQuestionActuel(@PathVariable String codeInvitation){
        PartieKahoot partieKahoot = this.partieKahootService.getPartieKahootByIdOrCodeInvitation(codeInvitation);
        partieKahoot = this.partieKahootService.maintenirAJourQuestionActuel(partieKahoot);
        if(partieKahoot.getStatus() == Status.Pending){
            throw new PartyNotStartedException();
        }
        if(partieKahoot.getStatus() == Status.Ended){
            throw new PartyIsEndedException();
        }
        return this.modelMapper.map(partieKahoot, PartieKahootQuestionDTO.class);
    }

    @PostMapping(value = "/{codeInvitation}/reponse", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ReponseValideDTO repondre(@PathVariable String codeInvitation, @RequestBody @Valid PartieReponse request){
        PartieKahoot partieKahoot = this.partieKahootService.getPartieKahootByIdOrCodeInvitation(codeInvitation);
        partieKahoot = this.partieKahootService.maintenirAJourQuestionActuel(partieKahoot);
        Joueur joueur = this.joueurService.findById(request.getIdJoueur());

        if(partieKahoot.getStatus() == Status.Pending){
            throw new PartyNotStartedException();
        }
        if(partieKahoot.getStatus() == Status.Ended){
            throw new PartyIsEndedException();
        }

        if(partieKahoot.getJoueurs().stream().noneMatch(joueur1 -> Objects.equals(joueur1.getId(), joueur.getId()))){
            throw new JoueurPasDansPartieException();
        }

        Question questionActuel = partieKahoot.getQuestionActuel();
        if(partieKahoot.getReponses().stream()
                .filter(partieReponse -> Objects.equals(partieReponse.getJoueur().getId(), joueur.getId()))
                .anyMatch(partieReponse -> Objects.equals(partieReponse.getQuestion().getId(), questionActuel.getId()))){
            throw new QuestionDejaReponduException();
        }


        Reponse reponse = reponseService.findById(request.getIdReponse());
        if(!Objects.equals(reponse.getQuestion().getId(), partieKahoot.getQuestionActuel().getId())){
            throw new ReponseIncoherenteException();
        }

        Calendar actualDate = Calendar.getInstance();
        actualDate.setTime(new Date());
        if(actualDate.after(partieKahoot.getTempsLimiteReponse())){
            throw new ReponseTempsLimiteDepasseException();
        }

        ReponsePartieKahoot reponsePartie = new ReponsePartieKahoot();
        reponsePartie.setId(new ReponsePartieKahootKey(reponse.getQuestion().getId(), partieKahoot.getId(), joueur.getId()));
        reponsePartie.setQuestion(reponse.getQuestion());
        reponsePartie.setPartie(partieKahoot);
        reponsePartie.setReponse(reponse);
        reponsePartie.setJoueur(joueur);
        this.reponsePartieKahootRepository.save(reponsePartie);

        ScorePartieKahootJoueur scorePartieKahootJoueur = partieKahoot.getScores().stream().filter(socre -> socre.getJoueur().getId() == joueur.getId())
                                                            .findFirst()
                                                            .orElseThrow(() -> new ScorePartieKahootJoueurNotFound(joueur.getId()));

        scorePartieKahootJoueur.setScore(scorePartieKahootJoueur.getScore() + this.partieKahootService.getScore(partieKahoot.getTempsLimiteReponse(), reponse));
        this.scorePartieKahootJoueurRepository.save(scorePartieKahootJoueur);

        return this.modelMapper.map(reponse, ReponseValideDTO.class);
    }

}
