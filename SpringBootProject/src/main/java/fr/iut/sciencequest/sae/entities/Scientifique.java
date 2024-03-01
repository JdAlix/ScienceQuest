package fr.iut.sciencequest.sae.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@RequiredArgsConstructor
//@Relation(collectionRelation = "scientifiques", itemRelation = "scientifique")
@Data
@Entity
@Table(name = "scientifique")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Scientifique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="iddifficulte", nullable = false)
    private Difficulte difficulte;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="idthematique", nullable = false)
    private Thematique thematique;

    @Column(name = "photo")
    private String pathToPhoto;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String descriptif;

    @Column(name = "datenaissance", nullable = false)
    private Date dateNaissance;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sexe sexe;

    @Column(name = "ratiotrouvee")
    private BigDecimal ratioTrouve = BigDecimal.ZERO;
}
