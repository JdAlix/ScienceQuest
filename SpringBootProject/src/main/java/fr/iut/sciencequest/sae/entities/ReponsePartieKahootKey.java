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
public class ReponsePartieKahootKey implements Serializable {
    @Column(name="idquestion")
    private Integer idQuestion;

    @Column(name="idpartie")
    private Integer idPartie;

    @Column(name="idjoueur")
    private Integer idJoueur;
}
