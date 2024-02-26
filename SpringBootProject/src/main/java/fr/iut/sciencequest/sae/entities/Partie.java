package fr.iut.sciencequest.sae.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="partie")
public class Partie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "codeinvitation")
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
