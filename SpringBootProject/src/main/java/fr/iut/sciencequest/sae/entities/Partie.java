package fr.iut.sciencequest.sae.entities;

import fr.iut.sciencequest.sae.entities.joueur.Joueur;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="partie")
public class Partie extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "codeinvitation", unique = true)
    private String codeInvitation;

    @OneToMany(mappedBy = "id")
    @Fetch(FetchMode.JOIN) // Sinon crash (Could not write JSON: failed to lazily initialize a collection of role)
    private List<Joueur> joueurs;

    @NotNull
    @ManyToOne
    @JoinColumn(name="idjeu")
    private Jeu jeu;
}
