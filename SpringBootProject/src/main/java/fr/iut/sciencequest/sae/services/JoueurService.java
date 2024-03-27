package fr.iut.sciencequest.sae.services;

import fr.iut.sciencequest.sae.entities.Joueur;
import fr.iut.sciencequest.sae.entities.Thematique;
import fr.iut.sciencequest.sae.exceptions.notFound.JoueurNotFoundException;
import fr.iut.sciencequest.sae.exceptions.notFound.ThematiqueNotFoundException;
import fr.iut.sciencequest.sae.repositories.JoueurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class JoueurService {
    private final JoueurRepository joueurRepository;

    public Joueur findById(int id) {
        return this.joueurRepository.findById(id).orElseThrow(() ->
                new JoueurNotFoundException(id)
        );
    }
}
