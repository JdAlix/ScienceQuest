package fr.iut.sciencequest.sae.assemblers;

import fr.iut.sciencequest.sae.controllers.QuestionController;
import fr.iut.sciencequest.sae.entities.Question;
import fr.iut.sciencequest.sae.dto.QuestionDTO;
import jakarta.annotation.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class QuestionModelAssembler extends RepresentationModelAssemblerSupport<Question, QuestionDTO> {

    public QuestionModelAssembler() {
        super(QuestionController.class, QuestionDTO.class);
    }

    @Override
    @NonNull
    public QuestionDTO toModel(@Nullable Question entity) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(entity, QuestionDTO.class);
    }
}
