package fr.iut.sciencequest.sae.dto.utilisateur;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.iut.sciencequest.sae.dto.partie.PartieDTO;
import jakarta.validation.constraints.Email;
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
public class UtilisateurWithPasswordDTO extends RepresentationModel<UtilisateurWithPasswordDTO> {
    @NotNull
    private Integer id;

    @Email
    @NotNull
    private String email;

    @NotBlank
    private String motDePasse;

    @NotBlank
    private String pseudo;

    private PartieDTO partie;
}
