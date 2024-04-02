package fr.iut.sciencequest.sae.repositories;

import fr.iut.sciencequest.sae.entities.Utilisateur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findUtilisateurByEmail(String email);
}