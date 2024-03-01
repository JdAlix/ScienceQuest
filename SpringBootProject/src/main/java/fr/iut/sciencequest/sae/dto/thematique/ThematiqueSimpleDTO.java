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
public class ThematiqueSimpleDTO extends RepresentationModel<ThematiqueSimpleDTO> {
    @NotNull
    private Integer id;

    @NotBlank
    private String libelle;
}
