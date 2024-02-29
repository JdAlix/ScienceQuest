package fr.iut.sciencequest.sae.entities.indice;

import fr.iut.sciencequest.sae.entities.BaseEntity;
import fr.iut.sciencequest.sae.entities.scientifique.Scientifique;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="indice")
public class Indice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(groups = {IValidateOnlyLibelle.class})
    private String libelle;

    @ManyToOne
    @NotNull
    @JoinColumn(name="idscientifique", nullable = false)
    private Scientifique scientifique;
    
    public Indice(int id, String libelle) { // Used for projection
        this.id = id;
        this.libelle = libelle;
    }
}

