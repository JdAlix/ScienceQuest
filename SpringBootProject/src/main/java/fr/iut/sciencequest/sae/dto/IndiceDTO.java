package fr.iut.sciencequest.sae.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.iut.sciencequest.sae.entities.Reponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;


import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IndiceDTO extends RepresentationModel<QuestionDTO> {
    @NotNull
    private int id;
    private String question;
    @NotEmpty
    private List<Reponse> reponses;
}
