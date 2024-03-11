package fr.iut.sciencequest.sae.dto.difficulte;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DifficulteIdOnlyDTO extends RepresentationModel<DifficulteIdOnlyDTO> {
    @NotNull
    private Integer id;
}
