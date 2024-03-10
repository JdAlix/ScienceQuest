package fr.iut.sciencequest.sae.services;

import fr.iut.sciencequest.sae.entities.Question;
import fr.iut.sciencequest.sae.repositories.QuestionRepository;
import fr.iut.sciencequest.sae.repositories.ReponseRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final ReponseRepository reponseRepository;

    public Page<Question> findAll(Pageable p) {
        return questionRepository.findAll(p);
    }

    public Page<Question> findWithCriteria(Pageable page, Integer scientifiqueId) {
        return questionRepository.findAllQuestionsByScientifiqueId(page, scientifiqueId);
    }

}
