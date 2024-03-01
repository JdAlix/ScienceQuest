package fr.iut.sciencequest.sae.dto.indice;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.iut.sciencequest.sae.dto.scientifique.ScientifiqueIdOnlyDTO;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IndiceWithoutIdAndScientifiqueIdOnlyForPatchDTO extends RepresentationModel<IndiceWithoutIdAndScientifiqueIdOnlyForPatchDTO> {

    private String libelle;

    @Valid
    private ScientifiqueIdOnlyDTO scientifique;
}
