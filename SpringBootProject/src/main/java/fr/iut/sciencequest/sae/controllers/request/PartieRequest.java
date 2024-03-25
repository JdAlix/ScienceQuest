package fr.iut.sciencequest.sae.controllers.request;

import lombok.Data;

import java.util.List;

@Data
public class PartieRequest {
    private Integer idJeu;
    private String pseudo;
    private List<Integer> thematiques;
    private Integer idDifficulte;
}
