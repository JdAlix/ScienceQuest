package fr.iut.sciencequest.sae.dto.reponse;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class ReponseDTO extends RepresentationModel<ReponseDTO> {
    @NotNull
    private int id;

    @NotNull
    @NotBlank
    private String reponse;
}
