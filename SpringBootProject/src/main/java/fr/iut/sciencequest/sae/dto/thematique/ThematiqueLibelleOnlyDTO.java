package fr.iut.sciencequest.sae.dto.thematique;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ThematiqueLibelleOnlyDTO extends RepresentationModel<ThematiqueLibelleOnlyDTO> {
    @NotBlank
    private String libelle;
}
