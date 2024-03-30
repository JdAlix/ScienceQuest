package fr.iut.sciencequest.sae.dto.partieKahoot;

import fr.iut.sciencequest.sae.entities.Status;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PartieKahootStatusDTO extends RepresentationModel<PartieKahootStatusDTO> {
    @NotNull
    private Status status;
}
