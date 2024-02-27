package fr.iut.sciencequest.sae.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="jeu")
public class Jeu {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(unique = true)
    private String nom;

    @Column(name = "nbrparties")
    @Min(0)
    private int nbrParties = 0;
}
