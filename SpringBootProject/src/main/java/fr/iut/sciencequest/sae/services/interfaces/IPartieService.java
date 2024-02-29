package fr.iut.sciencequest.sae.services.interfaces;

import fr.iut.sciencequest.sae.entities.Partie;

public interface IPartieService {
    Partie findById(int id);
    Partie save(Partie p);
}
