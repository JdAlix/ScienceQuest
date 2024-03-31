package fr.iut.sciencequest.sae.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class ScorePartieKahootJoueurKey implements Serializable {
    @Column(name="idjoueur")
    private Integer idJoueur;

    @Column(name="idpartie")
    private Integer idPartie;
}
