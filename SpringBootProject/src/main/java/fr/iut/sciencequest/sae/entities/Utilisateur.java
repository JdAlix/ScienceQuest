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
public class Utilisateur extends Joueur{
    private String email;
    @Column(name = "motdepasse")
    private String motDePasse;
    private String pseudo;
}

