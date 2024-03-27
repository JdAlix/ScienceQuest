package fr.iut.sciencequest.sae.dto.invite;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.iut.sciencequest.sae.dto.partie.PartieDTO;
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
public class InviteWithPseudoOnlyDTO extends RepresentationModel<InviteWithPseudoOnlyDTO> {
    @NotBlank
    private String pseudo;
}
