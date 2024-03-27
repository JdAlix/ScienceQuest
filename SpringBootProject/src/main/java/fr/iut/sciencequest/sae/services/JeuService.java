package fr.iut.sciencequest.sae.services;

import fr.iut.sciencequest.sae.entities.Jeu;
import fr.iut.sciencequest.sae.entities.Joueur;
import fr.iut.sciencequest.sae.exceptions.notFound.JeuNotFoundException;
import fr.iut.sciencequest.sae.exceptions.notFound.JoueurNotFoundException;
import fr.iut.sciencequest.sae.repositories.JeuRepository;
import fr.iut.sciencequest.sae.repositories.JoueurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class JeuService {
    private final JeuRepository jeuRepository;

    public Jeu findById(int id) {
        return this.jeuRepository.findById(id).orElseThrow(() ->
                new JeuNotFoundException(id)
        );
    }
}
