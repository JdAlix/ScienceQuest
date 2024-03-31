package fr.iut.sciencequest.sae.controllers.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PartieReponse {
    @NotNull
    private int idReponse;
    @NotNull
    private int idJoueur;
}
