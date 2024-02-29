package fr.iut.sciencequest.sae.services;

import fr.iut.sciencequest.sae.entities.Question;
import fr.iut.sciencequest.sae.repositories.QuestionRepository;
import fr.iut.sciencequest.sae.services.interfaces.IQuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuestionService implements IQuestionService {
    private static final int PAGE_SIZE = 1;
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Page<Question> findAll(Integer page) {
        Pageable paging = PageRequest.of(page, PAGE_SIZE);
        return questionRepository.findAll(paging);
    }
}
