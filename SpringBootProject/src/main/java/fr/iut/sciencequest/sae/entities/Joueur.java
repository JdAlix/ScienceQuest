package fr.iut.sciencequest.sae.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Inheritance ( strategy = InheritanceType.JOINED)
@Entity
@Table(name="joueur")
public class Joueur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String pseudo;

    @ManyToOne
    @JoinColumn(name = "idpartie")
    private Partie partie;
}
