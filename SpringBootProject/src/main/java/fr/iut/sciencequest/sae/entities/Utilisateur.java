package fr.iut.sciencequest.sae.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="utilisateur")
@PrimaryKeyJoinColumn(name = "idjoueur")
public class Utilisateur extends Joueur {
    @Column(unique = true)
    private String email;

    @Column(name = "password")
    private String motDePasse;
}

