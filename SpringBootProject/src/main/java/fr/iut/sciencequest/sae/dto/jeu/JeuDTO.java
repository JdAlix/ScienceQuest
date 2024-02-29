package fr.iut.sciencequest.sae.dto.jeu;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JeuDTO extends RepresentationModel<JeuDTO> {
    @NotNull
    private int id;

    @NotBlank
    private String nom;

    @Min(0)
    private int nbrParties = 0;
}
