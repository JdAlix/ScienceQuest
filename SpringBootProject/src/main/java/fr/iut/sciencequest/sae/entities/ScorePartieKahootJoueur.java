package fr.iut.sciencequest.sae.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="scorepartiekahootjoueur")
public class ScorePartieKahootJoueur {
    @EmbeddedId
    private ScorePartieKahootJoueurKey id = new ScorePartieKahootJoueurKey();

    @ManyToOne
    @MapsId("idJoueur")
    @JoinColumn(name = "idjoueur")
    private Joueur joueur;

    @ManyToOne
    @MapsId("idPartie")
    @JoinColumn(name="idpartie")
    private Partie partie;

    private Integer score = 0;
}
