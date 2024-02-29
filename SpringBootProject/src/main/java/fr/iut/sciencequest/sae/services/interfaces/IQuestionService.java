package fr.iut.sciencequest.sae.services.interfaces;

import fr.iut.sciencequest.sae.entities.Question;
import org.springframework.data.domain.Page;

public interface IQuestionService {
    Page<Question> findAll(Integer page);
}
