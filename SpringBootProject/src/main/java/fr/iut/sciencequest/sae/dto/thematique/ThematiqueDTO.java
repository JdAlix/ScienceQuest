package fr.iut.sciencequest.sae.dto.thematique;

import fr.iut.sciencequest.sae.entities.Scientifique;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ThematiqueDTO extends RepresentationModel<ThematiqueDTO> {
    @NotNull
    private Integer id;

    @NotBlank
    private String libelle;

    private Iterable<Scientifique> scientifiques;
}
