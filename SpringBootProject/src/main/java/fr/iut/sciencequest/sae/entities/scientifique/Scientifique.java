package fr.iut.sciencequest.sae.entities.scientifique;

import fr.iut.sciencequest.sae.entities.BaseEntity;
import fr.iut.sciencequest.sae.entities.Difficulte;
import fr.iut.sciencequest.sae.entities.Sexe;
import fr.iut.sciencequest.sae.entities.Thematique;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.hateoas.server.core.Relation;

import java.util.Date;

@AllArgsConstructor
@RequiredArgsConstructor
@Relation(collectionRelation = "scientifiques", itemRelation = "scientifique")
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "scientifique")
public class Scientifique extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="iddifficulte")
    private Difficulte difficulte;

    @ManyToOne
    @JoinColumn(name="idthematique")
    private Thematique thematique;

    @Column(name = "photo")
    private String pathToPhoto;

    private String nom;

    private String prenom;

    private String descriptif;

    @Column(name = "datenaissance")
    private Date dateNaissance;

    @Enumerated(EnumType.STRING)
    private Sexe sexe;

    @Column(name = "ratiotrouvee")
    private double ratioTrouve = 0.0;
}
