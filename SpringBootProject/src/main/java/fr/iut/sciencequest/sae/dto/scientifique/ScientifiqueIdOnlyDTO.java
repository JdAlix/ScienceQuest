package fr.iut.sciencequest.sae.dto.scientifique;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.lang.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ScientifiqueIdOnlyDTO extends RepresentationModel<ScientifiqueIdOnlyDTO> {
    @NotNull
    private Integer id;
}
