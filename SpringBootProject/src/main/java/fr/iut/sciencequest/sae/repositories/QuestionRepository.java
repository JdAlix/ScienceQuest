package fr.iut.sciencequest.sae.repositories;

import fr.iut.sciencequest.sae.entities.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    @Query("SELECT q FROM Question q JOIN Reponse r ON q.id = r.id WHERE r.scientifique.id = :scientifiqueId")
    Page<Question> findAllQuestionsByScientifiqueId(Pageable page, @Param("scientifiqueId") Integer scientifiqueId);
}
