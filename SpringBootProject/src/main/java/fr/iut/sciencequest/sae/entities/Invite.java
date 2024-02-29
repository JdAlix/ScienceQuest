package fr.iut.sciencequest.sae.entities;


import fr.iut.sciencequest.sae.entities.joueur.Joueur;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
@Entity
@Table(name = "invite")
@PrimaryKeyJoinColumn(name = "idjoueur")
public class Invite extends Joueur {
}
