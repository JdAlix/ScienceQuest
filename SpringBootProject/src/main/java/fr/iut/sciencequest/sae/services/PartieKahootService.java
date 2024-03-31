package fr.iut.sciencequest.sae.services;

import fr.iut.sciencequest.sae.entities.PartieKahoot;
import fr.iut.sciencequest.sae.entities.Question;
import fr.iut.sciencequest.sae.entities.QuestionPartieKahoot;
import fr.iut.sciencequest.sae.entities.Status;
import fr.iut.sciencequest.sae.exceptions.DuplicatedIdException;
import fr.iut.sciencequest.sae.exceptions.notFound.PartieKahootNotFoundException;
import fr.iut.sciencequest.sae.exceptions.partie.PartyNotStartedException;
import fr.iut.sciencequest.sae.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PartieKahootService {
    private static final int TEMPS_REPONSE_QUESTION = 60; //secondes
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
        if(partieKahoot.getStatus() != Status.Started){
            throw new PartyNotStartedException();
        }
        Optional<QuestionPartieKahoot> questionPartieKahoot = partieKahoot.getQuestions().stream().filter(q -> !q.isAEtePose()).findFirst();
        if (questionPartieKahoot.isEmpty()){
            partieKahoot.setStatus(Status.Ended);
            partieKahoot.setQuestionActuel(null);
            partieKahoot.setTempsLimiteReponse(null);
        }else{
            Calendar tempsLimiteReponse = Calendar.getInstance();
            tempsLimiteReponse.setTime(new Date());
            tempsLimiteReponse.add(Calendar.SECOND, PartieKahootService.TEMPS_REPONSE_QUESTION);

            questionPartieKahoot.get().setAEtePose(true);
            partieKahoot.setQuestionActuel(questionPartieKahoot.get().getQuestion());
            partieKahoot.setTempsLimiteReponse(tempsLimiteReponse);

            this.questionPartieKahootRepository.save(questionPartieKahoot.get());
        }
        return this.update(partieKahoot);
    }

}
