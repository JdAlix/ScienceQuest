package fr.iut.sciencequest.sae.repositories;

import fr.iut.sciencequest.sae.entities.QuestionPartieKahoot;
import fr.iut.sciencequest.sae.entities.QuestionPartieKahootKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionPartieKahootRepository extends CrudRepository<QuestionPartieKahoot, QuestionPartieKahootKey> {}
