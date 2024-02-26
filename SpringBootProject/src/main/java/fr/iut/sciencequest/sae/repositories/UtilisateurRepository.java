package fr.iut.sciencequest.sae.repositories;

import fr.iut.sciencequest.sae.entities.Utilisateur;
import org.springframework.data.repository.CrudRepository;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {}