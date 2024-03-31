package fr.iut.sciencequest.sae.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name="joueur")
public class Joueur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String pseudo;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idpartieencours")
    @Fetch(FetchMode.JOIN)
    @EqualsAndHashCode.Exclude
    private Partie partieEnCours;

    @OneToMany(mappedBy = "joueur")
    private List<ScorePartieKahootJoueur> scores;
}
