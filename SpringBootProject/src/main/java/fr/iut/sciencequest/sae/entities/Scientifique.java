package fr.iut.sciencequest.sae.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.Date;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name = "scientifique")
public class Scientifique {
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

    private String nom, prenom, descriptif;

    @Column(name = "datenaissance")
    private Date dateNaissance;

    private char sexe;

    @Column(name = "ratiotrouvee")
    private double ratioTrouve;
}
