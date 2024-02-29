package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.entities.Question;
import fr.iut.sciencequest.sae.exceptions.IncorrectPageException;
import fr.iut.sciencequest.sae.services.QuestionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/questions")
public class QuestionController extends Controller {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CollectionModel<EntityModel<Question>> getAllQuestions(@RequestParam(name = "page") Optional<Integer> page) {
        try {
            return getPageableCollectionModel(questionService.findAll(page.orElse(0)), page.orElse(0), "getAllQuestions");
        } catch (IllegalArgumentException e) {
            throw new IncorrectPageException("numéro de page incorrect");
        }
    }
}

