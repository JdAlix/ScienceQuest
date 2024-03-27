package fr.iut.sciencequest.sae.services;

import fr.iut.sciencequest.sae.entities.Invite;
import fr.iut.sciencequest.sae.entities.Thematique;
import fr.iut.sciencequest.sae.exceptions.DuplicatedFieldException;
import fr.iut.sciencequest.sae.exceptions.DuplicatedIdException;
import fr.iut.sciencequest.sae.exceptions.notFound.InviteNotFoundException;
import fr.iut.sciencequest.sae.exceptions.notFound.ThematiqueNotFoundException;
import fr.iut.sciencequest.sae.repositories.InviteRepository;
import fr.iut.sciencequest.sae.repositories.JoueurRepository;
import fr.iut.sciencequest.sae.repositories.ThematiqueRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class InviteService {
    private final InviteRepository inviteRepository;
    private final JoueurRepository joueurRepository;

    private void checkFieldsConstraints(Invite invite){
        if(this.joueurRepository.existsByPseudo(invite.getPseudo())){
            throw new DuplicatedFieldException("pseudo");
        }
    }
    public Invite update(Invite invite){
        if(!this.joueurRepository.existsById(invite.getId())){
            throw new InviteNotFoundException(invite.getId());
        }
        this.checkFieldsConstraints(invite);
        return this.inviteRepository.save(invite);
    }

    public Invite create(Invite invite){
        if(invite.getId() != null && this.joueurRepository.existsById(invite.getId())){
            throw new DuplicatedIdException();
        }
        this.checkFieldsConstraints(invite);
        return this.inviteRepository.save(invite);
    }
}
