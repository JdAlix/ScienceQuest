package fr.iut.sciencequest.sae.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Inheritance ( strategy = InheritanceType.JOINED)
@Entity
@Table(name="joueur")
public abstract class Joueur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String pseudo;
    //private Partie partie;
}
