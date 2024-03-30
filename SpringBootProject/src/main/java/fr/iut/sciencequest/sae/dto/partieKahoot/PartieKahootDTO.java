package fr.iut.sciencequest.sae.dto.partieKahoot;

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
public class PartieKahootDTO extends RepresentationModel<PartieKahootDTO> {
    @NotNull
    private Integer id;
    @NotEmpty
    private String codeInvitation;
    @NotEmpty
    private List<JoueurSimpleDTO> joueurs;
    @NotNull
    private JeuDTO jeu;
    @NotEmpty
    private List<ThematiqueSimpleDTO> thematiques;
    @NotNull
    private DifficulteSimpleDTO difficulte;
    @NotNull
    private Status status;

    private Question questionActuel;

    private List<Question> questions;
}
