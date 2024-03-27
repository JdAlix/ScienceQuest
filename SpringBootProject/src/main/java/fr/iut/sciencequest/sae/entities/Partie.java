package fr.iut.sciencequest.sae.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="partie")
public class Partie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codeinvitation", unique = true, nullable = false) //default value : see Schema.sql
    private String codeInvitation;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "idpartie")
    private List<Joueur> joueurs;

    @ManyToOne
    @JoinColumn(name="idjeu", nullable = false)
    private Jeu jeu;

    @Column(name = "status") //default value : see Schema.sql
    private String status;

    @Column(name = "datecreation") //default value : see Schema.sql
    private Date dateCreation;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "thematiqueselectionnee",
            joinColumns = @JoinColumn(name = "idpartie"),
            inverseJoinColumns = @JoinColumn(name="idthematique")
    )
    List<Thematique> thematiques;
}
