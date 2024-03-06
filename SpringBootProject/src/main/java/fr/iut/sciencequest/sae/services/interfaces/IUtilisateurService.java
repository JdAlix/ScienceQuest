package fr.iut.sciencequest.sae.services.interfaces;

import fr.iut.sciencequest.sae.dto.utilisateur.UtilisateurDTO;
import fr.iut.sciencequest.sae.dto.utilisateur.UtilisateurWithPasswordDTO;
import fr.iut.sciencequest.sae.entities.Utilisateur;

public interface IUtilisateurService {
    UtilisateurDTO save(UtilisateurWithPasswordDTO user);
    UtilisateurDTO login(UtilisateurWithPasswordDTO user);

    Utilisateur findUserByEmail(String email);
}
