package fr.iut.sciencequest.sae.dto.partieKahoot;

import fr.iut.sciencequest.sae.dto.scorePartieJoueur.ScorePartieKahootJoueurDTO;
import fr.iut.sciencequest.sae.entities.Status;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PartieKahootStatusDTO extends RepresentationModel<PartieKahootStatusDTO> {
    @NotNull
    private Status status;

    private List<ScorePartieKahootJoueurDTO> scores;
}
