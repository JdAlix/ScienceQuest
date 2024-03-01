package fr.iut.sciencequest.sae.dto.scientifique;

import fr.iut.sciencequest.sae.dto.difficulte.DifficulteSimpleDTO;
import fr.iut.sciencequest.sae.dto.thematique.ThematiqueSimpleDTO;
import fr.iut.sciencequest.sae.entities.Sexe;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ScientifiqueDTO extends RepresentationModel<ScientifiqueDTO> {
    @NotNull
    private Integer id;
    
    @NotNull
    private DifficulteSimpleDTO difficulte;

    @NotNull
    private ThematiqueSimpleDTO thematique;

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

    @Size(max=1)
    private BigDecimal ratioTrouve;
}
