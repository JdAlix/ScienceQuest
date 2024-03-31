package fr.iut.sciencequest.sae.services;

import fr.iut.sciencequest.sae.entities.Difficulte;
import fr.iut.sciencequest.sae.entities.Question;
import fr.iut.sciencequest.sae.entities.Reponse;
import fr.iut.sciencequest.sae.entities.Thematique;
import fr.iut.sciencequest.sae.exceptions.notFound.ReponseNotFoundException;
import fr.iut.sciencequest.sae.exceptions.notFound.ThematiqueNotFoundException;
import fr.iut.sciencequest.sae.repositories.QuestionRepository;
import fr.iut.sciencequest.sae.repositories.ReponseRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ReponseService {
    private final ReponseRepository reponseRepository;
    public Reponse findById(int id) {
        return this.reponseRepository.findById(id).orElseThrow(() ->
                new ReponseNotFoundException(id)
        );
    }

}
