package fr.iut.sciencequest.sae.controllers.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PartieAddJoueurRequest {
    @NotNull
    private int idJoueur;
}
