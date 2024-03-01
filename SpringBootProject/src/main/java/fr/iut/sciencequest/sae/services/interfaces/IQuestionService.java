package fr.iut.sciencequest.sae.services.interfaces;

import fr.iut.sciencequest.sae.entities.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IQuestionService {
    Page<Question> findAll(Pageable page);
    Page<Question> findWithCriteria(Pageable page, Integer scientifiqueId);
}
