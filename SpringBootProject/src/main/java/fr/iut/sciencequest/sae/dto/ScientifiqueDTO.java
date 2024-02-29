package fr.iut.sciencequest.sae.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.iut.sciencequest.sae.entities.Difficulte;
import fr.iut.sciencequest.sae.entities.Sexe;
import fr.iut.sciencequest.sae.entities.Thematique;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScientifiqueDTO extends RepresentationModel<ScientifiqueDTO> {
    @NotNull
    private int id;
    
    @NotNull
    private Difficulte difficulte;

    @NotNull
    private Thematique thematique;

    @URL
    private String pathToPhoto;

    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    @NotBlank
    private String descriptif;

    @NotEmpty
    private Date dateNaissance;

    @NotBlank
    private Sexe sexe;

    @Size(min=0, max=1)
    private double ratioTrouvee;
}
