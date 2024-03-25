package fr.iut.sciencequest.sae.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Joueur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String pseudo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idpartie")
    @JsonIgnore
    private Partie partie;
}
