package fr.iut.sciencequest.sae.repositories;

import fr.iut.sciencequest.sae.entities.Utilisateur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {
    Utilisateur findUtilisateurByEmail(String email);
}