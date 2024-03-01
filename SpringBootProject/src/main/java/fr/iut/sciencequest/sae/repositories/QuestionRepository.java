package fr.iut.sciencequest.sae.repositories;

import fr.iut.sciencequest.sae.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {


}
