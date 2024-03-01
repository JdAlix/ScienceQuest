package fr.iut.sciencequest.sae.dto.indice;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IndiceLibelleOnlyDTO extends RepresentationModel<IndiceLibelleOnlyDTO> {
    @NotBlank
    private String libelle;
}
