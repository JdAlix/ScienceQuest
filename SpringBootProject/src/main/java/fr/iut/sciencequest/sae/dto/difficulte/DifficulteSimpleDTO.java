package fr.iut.sciencequest.sae.dto.difficulte;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DifficulteSimpleDTO extends RepresentationModel<DifficulteSimpleDTO> {
    @NotNull
    private Integer id;

    @NotBlank
    private String libelle;
}
