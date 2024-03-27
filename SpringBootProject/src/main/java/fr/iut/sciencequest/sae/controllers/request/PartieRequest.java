package fr.iut.sciencequest.sae.controllers.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PartieRequest {
    @NotNull
    private Integer idJeu;
    @NotNull
    private int idJoueur;

    private List<Integer> thematiques;
    private Integer idDifficulte;
}
