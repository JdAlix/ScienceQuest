package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.assemblers.QuestionModelAssembler;
import fr.iut.sciencequest.sae.entities.Question;
import fr.iut.sciencequest.sae.exceptions.IncorrectPageException;
import fr.iut.sciencequest.sae.models.QuestionModel;
import fr.iut.sciencequest.sae.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/questions")
public class QuestionController extends Controller {
    private final QuestionService questionService;

    @Autowired
    private QuestionModelAssembler questionModelAssembler;

    @Autowired
    private PagedResourcesAssembler<Question> pagedResourcesAssembler;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagedModel<QuestionModel> getAllQuestions(Pageable p) {
        try {
            Page<Question> questionPage = questionService.findAll(p);
            return pagedResourcesAssembler.toModel(questionPage, questionModelAssembler);
        } catch (IllegalArgumentException e) {
            throw new IncorrectPageException("numéro de page incorrect");
        }
    }
}

