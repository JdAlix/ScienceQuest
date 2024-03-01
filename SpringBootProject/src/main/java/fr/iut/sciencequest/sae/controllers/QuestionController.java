package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.ApplicationConfig;
import fr.iut.sciencequest.sae.assemblers.QuestionModelAssembler;
import fr.iut.sciencequest.sae.dto.question.QuestionDTO;
import fr.iut.sciencequest.sae.entities.Question;
import fr.iut.sciencequest.sae.services.interfaces.IQuestionService;
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
public class QuestionController {
    private final IQuestionService questionService;
    private final QuestionModelAssembler questionModelAssembler;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private final PagedResourcesAssembler<Question> pagedResourcesAssembler;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagedModel<QuestionDTO> getAllQuestions(@PageableDefault(size = ApplicationConfig.DEFAULT_PAGEABLE_SIZE) Pageable p,
                                                   @RequestParam(value = "scientifiqueId", defaultValue = "") Integer scientifiqueId) {

        Page<Question> questionPage = (scientifiqueId == null ? questionService.findAll(p) : questionService.findWithCriteria(p, scientifiqueId)); //TEMPORAIRE NE PAS ENLEVER
        return pagedResourcesAssembler.toModel(questionPage, questionModelAssembler);
    }
}

