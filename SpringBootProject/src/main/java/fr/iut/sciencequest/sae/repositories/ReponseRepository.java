package fr.iut.sciencequest.sae.repositories;

import fr.iut.sciencequest.sae.entities.Reponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReponseRepository extends CrudRepository<Reponse, Integer> { }
