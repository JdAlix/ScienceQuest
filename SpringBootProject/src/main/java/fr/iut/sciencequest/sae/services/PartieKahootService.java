package fr.iut.sciencequest.sae.services;

import fr.iut.sciencequest.sae.entities.*;
import fr.iut.sciencequest.sae.exceptions.DuplicatedIdException;
import fr.iut.sciencequest.sae.exceptions.notFound.PartieKahootNotFoundException;
import fr.iut.sciencequest.sae.exceptions.partie.PartyNotStartedException;
import fr.iut.sciencequest.sae.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PartieKahootService {
    private static final int TEMPS_REPONSE_QUESTION = 20; //secondes
    private static final int TEMPS_AFFICHAGE_SCORE = 5; //secondes
    private final PartieKahootRepository partieKahootRepository;
    private final QuestionPartieKahootRepository questionPartieKahootRepository;
    private final PartieRepository partieRepository;

    public PartieKahoot findById(int id) {
        return this.partieKahootRepository.findById(id).orElseThrow(() ->
                new PartieKahootNotFoundException(id)
        );
    }

    public PartieKahoot getPartieByCodeInvitation(String codeInvitation){
        return this.partieKahootRepository.getPartieByCodeInvitation(codeInvitation).orElseThrow(() -> new PartieKahootNotFoundException("codeInvitation", codeInvitation));
    }

    public PartieKahoot getPartieKahootByIdOrCodeInvitation(String codeInvitation){
        //try to get invitation from codeInvitation, if not : try with id
        PartieKahoot partie;
        try{
            partie = this.getPartieByCodeInvitation(codeInvitation);
        }catch (PartieKahootNotFoundException exceptionNotFoundByCodeInvitation){
            try{
                partie = this.findById(Integer.parseInt(codeInvitation));
            }catch (PartieKahootNotFoundException | NumberFormatException e2){
                throw exceptionNotFoundByCodeInvitation;
            }
        }
        return partie;
    }

    public PartieKahoot update(PartieKahoot partie){
        if(!this.partieRepository.existsById(partie.getId())){
            throw new PartieKahootNotFoundException(partie.getId());
        }
        PartieKahoot savedPartie = this.partieKahootRepository.save(partie);
        return this.findById(savedPartie.getId());
    }

    public PartieKahoot create(PartieKahoot partie){
        if(partie.getId() != null && this.partieRepository.existsById(partie.getId())){
            throw new DuplicatedIdException();
        }
        PartieKahoot savedPartie = this.partieKahootRepository.save(partie);
        return this.findById(savedPartie.getId());
    }

    public PartieKahoot questionSuivante(PartieKahoot partieKahoot){
        if(partieKahoot.getStatus() != Status.Started && partieKahoot.getStatus() != Status.DisplayingScore ){
            throw new PartyNotStartedException();
        }
        Optional<QuestionPartieKahoot> questionSuivante = partieKahoot.getQuestions().stream().filter(q -> !q.isAEtePose()).findFirst();

        //reset
        partieKahoot.setStatus(Status.Started);
        partieKahoot.setTempsAffichageScore(null);

        if (questionSuivante.isEmpty()){
            partieKahoot.setStatus(Status.Ended);
            partieKahoot.setQuestionActuel(null);
            partieKahoot.setTempsLimiteReponse(null);
        }else{
            Calendar tempsLimiteReponse = Calendar.getInstance();
            tempsLimiteReponse.setTime(new Date());
            tempsLimiteReponse.add(Calendar.SECOND, PartieKahootService.TEMPS_REPONSE_QUESTION);

            questionSuivante.get().setAEtePose(true);
            partieKahoot.setQuestionActuel(questionSuivante.get().getQuestion());
            partieKahoot.setTempsLimiteReponse(tempsLimiteReponse);

            this.questionPartieKahootRepository.save(questionSuivante.get());
        }
        return this.update(partieKahoot);
    }

    public PartieKahoot afficherScore(PartieKahoot partieKahoot){
        Calendar tempsAffichacheScore = Calendar.getInstance();
        tempsAffichacheScore.setTime(new Date());
        tempsAffichacheScore.add(Calendar.SECOND, TEMPS_AFFICHAGE_SCORE);
        partieKahoot.setStatus(Status.DisplayingScore);
        partieKahoot.setTempsAffichageScore(tempsAffichacheScore);

        //remove question
        partieKahoot.setQuestionActuel(null);
        partieKahoot.setTempsLimiteReponse(null);

        return this.partieKahootRepository.save(partieKahoot);
    }

    public PartieKahoot maintenirAJourQuestionActuel(PartieKahoot partieKahoot){
        Calendar actualDate = Calendar.getInstance();
        actualDate.setTime(new Date());
        if(partieKahoot.getStatus() == Status.Started){
            Question questionActuel = partieKahoot.getQuestionActuel();
            boolean toutLeMondeARepondu = partieKahoot.getJoueurs().size() == partieKahoot.getReponses().stream().filter(partieReponse -> Objects.equals(questionActuel.getId(), partieReponse.getQuestion().getId())).count();
            if(actualDate.after(partieKahoot.getTempsLimiteReponse()) || toutLeMondeARepondu){
                partieKahoot = this.afficherScore(partieKahoot);
            }
        } else if (partieKahoot.getStatus() == Status.DisplayingScore) {
            if(actualDate.after(partieKahoot.getTempsAffichageScore())){
                partieKahoot = this.questionSuivante(partieKahoot);
            }
        }
        return partieKahoot;
    }

    public int getScore(Calendar tempsLimiteReponse,Reponse reponse){
        if(!reponse.getEstValide()) return 0;
        Calendar actualDate = Calendar.getInstance();
        actualDate.setTime(new Date());
        long secondsPourRepondre = ChronoUnit.SECONDS.between(actualDate.toInstant(), tempsLimiteReponse.toInstant());
        return Math.round((1f/secondsPourRepondre*PartieKahootService.TEMPS_REPONSE_QUESTION)*100);
    }

}
