package fr.iut.sciencequest.sae.dto.partie;

import fr.iut.sciencequest.sae.dto.jeu.JeuDTO;
import fr.iut.sciencequest.sae.dto.joueur.JoueurSimpleDTO;
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
    @NotNull
    private JeuDTO jeu;
}
