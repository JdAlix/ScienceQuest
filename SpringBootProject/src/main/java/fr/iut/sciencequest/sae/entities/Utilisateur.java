package fr.iut.sciencequest.sae.entities;


import fr.iut.sciencequest.sae.entities.joueur.Joueur;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @Email(message="Veuillez fournir une adresse mail valide")
    @NotNull
    @Column(unique = true)
    private String email;

    @NotBlank
    @Column(name = "password")
    private String motDePasse;
}

