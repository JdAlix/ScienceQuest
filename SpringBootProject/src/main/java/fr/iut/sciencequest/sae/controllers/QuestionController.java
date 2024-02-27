package fr.iut.sciencequest.sae.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import fr.iut.sciencequest.sae.entities.Question;
import fr.iut.sciencequest.sae.exceptions.IncorrectPageException;
import fr.iut.sciencequest.sae.repositories.QuestionRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@Controller
@RequestMapping("/api/v1/questions")
public class QuestionController {

    private static final int PAGE_SIZE = 10;
    private final QuestionRepository questionRepository;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CollectionModel<EntityModel<Question>> getAllQuestions(@RequestParam(name = "page") Optional<Integer> page, HttpServletRequest request) {
        try {
            Pageable paging = PageRequest.of(page.orElse(0), PAGE_SIZE);
            Page<Question> pagedResult = questionRepository.findAll(paging);

            List<EntityModel<Question>> questions = pagedResult.map(EntityModel::of).toList();

            Link selfLink = linkTo(methodOn(QuestionController.class).getAllQuestions(page, request)).withSelfRel().expand(page.map(Object::toString).orElse("0"));

            CollectionModel<EntityModel<Question>> result = CollectionModel.of(questions, selfLink);

            if (pagedResult.hasPrevious()) {
                Link prevLink = linkTo(methodOn(QuestionController.class).getAllQuestions(Optional.of(pagedResult.previousPageable().getPageNumber()), request)).withRel("previous");
                result.add(prevLink.expand(pagedResult.previousPageable().getPageNumber()));
            }

            if (pagedResult.hasNext()) {
                Link nextLink = linkTo(methodOn(QuestionController.class).getAllQuestions(Optional.of(pagedResult.nextPageable().getPageNumber()), request)).withRel("next");
                result.add(nextLink.expand(pagedResult.nextPageable().getPageNumber()));
            }

            return result;
        } catch (IllegalArgumentException e) {
            throw new IncorrectPageException("numéro de page incorrect");
        }
    }
}

