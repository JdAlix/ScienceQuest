package fr.iut.sciencequest.sae.repositories;

import fr.iut.sciencequest.sae.entities.Difficulte;
import fr.iut.sciencequest.sae.entities.Question;
import fr.iut.sciencequest.sae.entities.Thematique;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    @Query("SELECT q FROM Question q JOIN Reponse r ON q.id = r.id WHERE r.scientifique.id = :scientifiqueId")
    Page<Question> findAllQuestionsByScientifiqueId(Pageable page, @Param("scientifiqueId") Integer scientifiqueId);

    @Query("SELECT q FROM Question q JOIN Reponse r ON q.id = r.question.id JOIN Scientifique s ON s.thematique IN :thematiques AND s.id = r.scientifique.id WHERE s.difficulte = :difficulte  ORDER BY RANDOM() LIMIT :number")
    List<Question> getRandomQuestions(@Param("number") int number, @Param("thematiques") List<Thematique> thematiques,@Param("difficulte")Difficulte difficulte);
}
