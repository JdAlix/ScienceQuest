package fr.iut.sciencequest.sae.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
@Entity
@Table(name = "invite")
public class Invite extends Joueur{
}
