package fr.iut.sciencequest.sae.entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="thematique")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Thematique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String libelle;

    @JsonManagedReference
    @OneToMany(mappedBy = "thematique")
    @Fetch(FetchMode.JOIN)
    private List<Scientifique> scientifiques;

    @ManyToMany(mappedBy = "thematiques")
    List<Partie> parties;
}
