package fr.iut.sciencequest.sae.controllers.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PartieRequest {
    @NotNull
    private int idJoueur;

    @NotEmpty
    private List<Integer> thematiques;

    @NotNull
    private Integer idDifficulte;
}
