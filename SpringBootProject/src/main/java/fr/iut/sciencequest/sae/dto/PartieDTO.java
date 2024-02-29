package fr.iut.sciencequest.sae.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.iut.sciencequest.sae.entities.Jeu;
import fr.iut.sciencequest.sae.entities.joueur.Joueur;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartieDTO extends RepresentationModel<PartieDTO> {
    @NotNull
    private int id;
    @NotEmpty
    private String codeInvitation;
    @NotEmpty
    private Iterable<Joueur> joueurs;
    @NotEmpty
    private Jeu jeu;
}
