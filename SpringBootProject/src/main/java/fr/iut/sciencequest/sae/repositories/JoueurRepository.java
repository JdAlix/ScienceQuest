package fr.iut.sciencequest.sae.repositories;

import fr.iut.sciencequest.sae.entities.Joueur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoueurRepository extends CrudRepository<Joueur, Integer> {}
