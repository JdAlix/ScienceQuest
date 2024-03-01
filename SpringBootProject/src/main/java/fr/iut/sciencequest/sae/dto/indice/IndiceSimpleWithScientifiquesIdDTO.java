package fr.iut.sciencequest.sae.dto.indice;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.iut.sciencequest.sae.dto.scientifique.ScientifiqueIdOnlyDTO;
import jakarta.validation.Valid;
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
public class IndiceSimpleWithScientifiquesIdDTO extends RepresentationModel<IndiceSimpleWithScientifiquesIdDTO> {
    @NotNull
    private Integer id;

    @NotBlank
    private String libelle;

    @NotNull
    private ScientifiqueIdOnlyDTO scientifique;
}
