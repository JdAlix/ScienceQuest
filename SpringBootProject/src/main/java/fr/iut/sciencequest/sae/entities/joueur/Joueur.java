package fr.iut.sciencequest.sae.entities.joueur;

import fr.iut.sciencequest.sae.entities.BaseEntity;
import fr.iut.sciencequest.sae.entities.Partie;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Inheritance ( strategy = InheritanceType.JOINED)
@Entity
@Table(name="joueur")
public abstract class Joueur extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String pseudo;

    @ManyToOne
    @JoinColumn(name="idpartie")
    private Partie partie;
}
