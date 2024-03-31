package fr.iut.sciencequest.sae.repositories;

import fr.iut.sciencequest.sae.entities.ReponsePartieKahoot;
import fr.iut.sciencequest.sae.entities.ReponsePartieKahootKey;
import fr.iut.sciencequest.sae.entities.ScorePartieKahootJoueur;
import fr.iut.sciencequest.sae.entities.ScorePartieKahootJoueurKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReponsePartieKahootRepository extends CrudRepository<ReponsePartieKahoot, ReponsePartieKahootKey> {}
