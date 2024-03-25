package fr.iut.sciencequest.sae.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="partie")
public class Partie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codeinvitation", unique = true, nullable = false)
    private String codeInvitation;

    @OneToMany(mappedBy = "partie")
    @Fetch(FetchMode.JOIN) // Sinon crash (Could not write JSON: failed to lazily initialize a collection of T)
    private List<Joueur> joueurs;

    @ManyToOne
    @JoinColumn(name="idjeu", nullable = false)
    private Jeu jeu;

    @Column(name = "status")
    private String status;

    @Column(name = "datecreation")
    private Date dateCreation;
}
