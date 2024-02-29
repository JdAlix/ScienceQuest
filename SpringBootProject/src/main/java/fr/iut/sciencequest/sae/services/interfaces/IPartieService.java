package fr.iut.sciencequest.sae.services.interfaces;

import fr.iut.sciencequest.sae.entities.Partie;

import java.util.List;

public interface IPartieService {
    Partie findById(int id);
    Partie create(Integer idJeu, Integer idUtilisateur, List<Integer> thematiques, Integer idDifficulte);
}
