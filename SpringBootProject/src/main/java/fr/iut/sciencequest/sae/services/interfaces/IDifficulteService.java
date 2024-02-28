package fr.iut.sciencequest.sae.services.interfaces;

import fr.iut.sciencequest.sae.entities.Difficulte;

public interface IDifficulteService {
    Iterable<Difficulte> findAll();
}
