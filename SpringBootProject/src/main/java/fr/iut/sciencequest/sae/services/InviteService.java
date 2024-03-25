package fr.iut.sciencequest.sae.services;

import fr.iut.sciencequest.sae.entities.Joueur;
import fr.iut.sciencequest.sae.entities.Partie;
import fr.iut.sciencequest.sae.exceptions.PartyAlreadyStartedException;
import fr.iut.sciencequest.sae.exceptions.notFound.InviteNotFoundException;
import fr.iut.sciencequest.sae.repositories.JoueurRepository;
import fr.iut.sciencequest.sae.repositories.PartieRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@AllArgsConstructor
@Service
public class InviteService {
    private final PartieRepository partieRepository;
    private final JoueurRepository joueurRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public HashMap<String, Object> findByInvite(String invite, String pseudo) {
        Partie partie = this.partieRepository.getPartieByCodeInvitation(invite).orElseThrow(() -> new InviteNotFoundException(invite));

        if(!partie.getStatus().equals("pending")) throw new PartyAlreadyStartedException();

        HashMap<String, Object> response = new HashMap<>();
        response.put("partieId", partie.getId());

        Joueur joueur = new Joueur();
        joueur.setPseudo(pseudo);

        joueur = joueurRepository.save(joueur);

        entityManager.createNativeQuery("UPDATE joueur SET idpartie = ? WHERE id = ?")
                .setParameter(1, partie.getId())
                .setParameter(2, joueur.getId())
                .executeUpdate();

        response.put("joueurId", joueur.getId());

        return response;
    }
}
