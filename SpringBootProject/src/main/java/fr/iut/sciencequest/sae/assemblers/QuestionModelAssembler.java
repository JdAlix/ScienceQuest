package fr.iut.sciencequest.sae.assemblers;

import fr.iut.sciencequest.sae.controllers.QuestionController;
import fr.iut.sciencequest.sae.entities.Question;
import fr.iut.sciencequest.sae.models.QuestionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class QuestionModelAssembler extends RepresentationModelAssemblerSupport<Question, QuestionModel> {

    public QuestionModelAssembler() {
        super(QuestionController.class, QuestionModel.class);
    }

    @Override
    public QuestionModel toModel(Question entity) {
        QuestionModel questionModel = instantiateModel(entity);

        questionModel.setQuestion(entity.getQuestion());
        questionModel.setId(entity.getId());
        questionModel.setReponses(entity.getReponses());

        return questionModel;
    }
}
