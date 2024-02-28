package fr.iut.sciencequest.sae.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.URL;
import org.springframework.hateoas.server.core.Relation;

import java.util.Date;

@AllArgsConstructor
@RequiredArgsConstructor
@Relation(collectionRelation = "scientifiques", itemRelation = "scientifique")
@Data
@Entity
@Table(name = "scientifique")
public class Scientifique {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="iddifficulte")
    private Difficulte difficulte;

    @NotNull
    @ManyToOne
    @JoinColumn(name="idthematique")
    private Thematique thematique;

    @URL
    @Column(name = "photo")
    private String pathToPhoto;

    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    @NotBlank
    private String descriptif;

    @NotBlank
    @Column(name = "datenaissance")
    private Date dateNaissance;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private Sexe sexe;

    @Column(name = "ratiotrouvee")
    @Size(min=0, max=1)
    private double ratioTrouve = 0.0;
}
