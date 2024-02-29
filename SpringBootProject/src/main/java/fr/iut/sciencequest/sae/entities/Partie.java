package fr.iut.sciencequest.sae.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="partie")
public class Partie extends BaseEntity {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "codeinvitation", unique = true)
    private String codeInvitation;
    /*private Jeu jeu;
    @Getter() private Set<Joueur> joueurs;

    public boolean add(Joueur joueur){
        return this.joueurs.add(joueur);
    }
    
    public boolean remove(Joueur joueur){
        return this.joueurs.remove(joueur);
    }
    */
}
