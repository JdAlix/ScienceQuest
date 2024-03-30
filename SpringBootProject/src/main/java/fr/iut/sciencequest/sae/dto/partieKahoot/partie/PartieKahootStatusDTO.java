package fr.iut.sciencequest.sae.dto.partieKahoot.partie;

import fr.iut.sciencequest.sae.dto.difficulte.DifficulteSimpleDTO;
import fr.iut.sciencequest.sae.dto.jeu.JeuDTO;
import fr.iut.sciencequest.sae.dto.joueur.JoueurSimpleDTO;
import fr.iut.sciencequest.sae.dto.thematique.ThematiqueSimpleDTO;
import fr.iut.sciencequest.sae.entities.Question;
import fr.iut.sciencequest.sae.entities.Status;
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
public class PartieKahootStatusDTO extends RepresentationModel<PartieKahootStatusDTO> {
    @NotNull
    private Status status;
}
