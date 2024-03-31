package fr.iut.sciencequest.sae.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="reponsepartiekahootjoueur")
public class ReponsePartieKahoot {
    @EmbeddedId
    private ReponsePartieKahootKey id = new ReponsePartieKahootKey();

    @JsonBackReference
    @ManyToOne
    @MapsId("idQuestion")
    @JoinColumn(name = "idquestion")
    private Question question;

    @JsonBackReference
    @ManyToOne
    @MapsId("idPartie")
    @JoinColumn(name="idpartie")
    private PartieKahoot partie;

    @JsonBackReference
    @ManyToOne
    @MapsId("idJoueur")
    @JoinColumn(name="idjoueur")
    private Joueur joueur;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idreponse")
    private Reponse reponse;
}
