package fr.iut.sciencequest.sae.repositories;

import fr.iut.sciencequest.sae.entities.Jeu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JeuRepository extends CrudRepository<Jeu, Integer> {}