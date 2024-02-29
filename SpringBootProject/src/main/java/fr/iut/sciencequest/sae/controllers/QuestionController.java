package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.ApplicationConfig;
import fr.iut.sciencequest.sae.assemblers.QuestionModelAssembler;
import fr.iut.sciencequest.sae.entities.Question;
import fr.iut.sciencequest.sae.exceptions.IncorrectPageException;
import fr.iut.sciencequest.sae.dto.QuestionDTO;
import fr.iut.sciencequest.sae.services.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/questions")
public class QuestionController extends Controller {
    private final QuestionService questionService;
    private final QuestionModelAssembler questionModelAssembler;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private final PagedResourcesAssembler<Question> pagedResourcesAssembler;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagedModel<QuestionDTO> getAllQuestions(@PageableDefault(size = ApplicationConfig.DEFAULT_PAGEABLE_SIZE) Pageable p) {
        try {
            Page<Question> questionPage = questionService.findAll(p);
            return pagedResourcesAssembler.toModel(questionPage, questionModelAssembler);
        } catch (IllegalArgumentException e) {
            throw new IncorrectPageException("numéro de page incorrect");
        }
    }
}

