package fr.iut.sciencequest.sae.services;

import fr.iut.sciencequest.sae.entities.Difficulte;
import fr.iut.sciencequest.sae.entities.Question;
import fr.iut.sciencequest.sae.entities.Thematique;
import fr.iut.sciencequest.sae.repositories.QuestionRepository;
import fr.iut.sciencequest.sae.repositories.ReponseRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Question> getRandomQuestions(int number, List<Thematique> thematiques, Difficulte difficulte){
        return this.questionRepository.getRandomQuestions(number, thematiques, difficulte);
    }

}
