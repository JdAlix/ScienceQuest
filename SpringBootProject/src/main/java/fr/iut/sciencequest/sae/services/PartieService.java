package fr.iut.sciencequest.sae.services;

import fr.iut.sciencequest.sae.entities.Joueur;
import fr.iut.sciencequest.sae.entities.Partie;
import fr.iut.sciencequest.sae.exceptions.MalformedPartyException;
import fr.iut.sciencequest.sae.exceptions.notFound.JeuNotFoundException;
import fr.iut.sciencequest.sae.exceptions.notFound.PartieNotFoundException;
import fr.iut.sciencequest.sae.repositories.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class PartieService {
    private final PartieRepository partieRepository;
    private final JeuRepository jeuRepository;
    private final JoueurRepository joueurRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ThematiqueRepository thematiqueRepository;
    private final DifficulteRepository difficulteRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public Partie findById(int id) {
        return this.partieRepository.findById(id).orElseThrow(() ->
                new PartieNotFoundException("Partie", id)
        );
    }

    @Transactional
    public Partie create(Integer idJeu, String pseudo, List<Integer> thematiques, Integer idDifficulte) {
        // Création du joueur
        Joueur joueur = new Joueur();
        joueur.setPseudo(pseudo);

        // Sauvegarder le joueur
        joueur = joueurRepository.save(joueur);

        // Création de la partie
        Partie partie = new Partie(
                0,
                entityManager.createNativeQuery("SELECT make_uid()").getSingleResult().toString(),
                List.of(joueur),
                jeuRepository.findById(idJeu).orElseThrow(() -> new JeuNotFoundException(idJeu)),
                "pending",
                new Date()
        );

        // Sauvegarder la partie pour générer le codeInvitation
        partie = partieRepository.save(partie);

        // Modification du joueur pour ajouter la partie
        entityManager.createNativeQuery("UPDATE joueur SET idpartie = ? WHERE id = ?")
                .setParameter(1, partie.getId())
                .setParameter(2, joueur.getId())
                .executeUpdate();

        // Récupérer la partie mise à jour avec le joueur
        return partieRepository.findById(partie.getId())
                .orElseThrow(MalformedPartyException::new);
    }

}
