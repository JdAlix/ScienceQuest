package fr.iut.sciencequest.sae.services;

import fr.iut.sciencequest.sae.entities.Partie;
import fr.iut.sciencequest.sae.entities.PartieKahoot;
import fr.iut.sciencequest.sae.exceptions.DuplicatedIdException;
import fr.iut.sciencequest.sae.exceptions.notFound.PartieKahootNotFoundException;
import fr.iut.sciencequest.sae.exceptions.notFound.PartieNotFoundException;
import fr.iut.sciencequest.sae.repositories.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PartieKahootService {
    private final PartieKahootRepository partieKahootRepository;
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

}
