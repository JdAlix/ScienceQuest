package fr.iut.sciencequest.sae.dto.partie;

import fr.iut.sciencequest.sae.dto.difficulte.DifficulteSimpleDTO;
import fr.iut.sciencequest.sae.dto.jeu.JeuDTO;
import fr.iut.sciencequest.sae.dto.joueur.JoueurSimpleDTO;
import fr.iut.sciencequest.sae.dto.thematique.ThematiqueSimpleDTO;
import fr.iut.sciencequest.sae.entities.Status;
import fr.iut.sciencequest.sae.entities.Thematique;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PartieDTO extends RepresentationModel<PartieDTO> {
    @NotNull
    private Integer id;
    @NotEmpty
    private String codeInvitation;
    @NotEmpty
    private List<JoueurSimpleDTO> joueurs;
    @NotEmpty
    private List<ThematiqueSimpleDTO> thematiques;
    @NotNull
    private DifficulteSimpleDTO difficulte;
    @NotNull
    private Status status;
}
