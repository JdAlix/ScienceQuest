package fr.iut.sciencequest.sae.dto.partieKahoot;

import fr.iut.sciencequest.sae.dto.question.QuestionDTO;
import fr.iut.sciencequest.sae.dto.reponse.ReponseSimpleDTO;
import fr.iut.sciencequest.sae.dto.scorePartieJoueur.ScorePartieKahootJoueurDTO;
import fr.iut.sciencequest.sae.entities.Reponse;
import fr.iut.sciencequest.sae.entities.Status;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Calendar;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PartieKahootQuestionDTO extends RepresentationModel<PartieKahootQuestionDTO> {
    private QuestionDTO questionActuel;
    private Calendar tempsLimiteReponse;
}
