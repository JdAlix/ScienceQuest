package fr.iut.sciencequest.sae.dto.scorePartieJoueur;

import fr.iut.sciencequest.sae.dto.joueur.JoueurSimpleDTO;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ScorePartieKahootJoueurDTO extends RepresentationModel<ScorePartieKahootJoueurDTO> {
    @NotNull
    private JoueurSimpleDTO joueur;

    private Integer score;
}
