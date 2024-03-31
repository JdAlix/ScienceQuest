package fr.iut.sciencequest.sae.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="partiekahoot")
@PrimaryKeyJoinColumn(name = "idpartie")
public class PartieKahoot extends Partie {

    @ManyToOne
    @JoinColumn(name="idquestionactuel")
    private Question questionActuel;

    //private Date tempsLimiteReponse;

    @JsonManagedReference
    @OneToMany(mappedBy = "partie", fetch = FetchType.EAGER)
    private List<QuestionPartieKahoot> questions;

    @JsonManagedReference
    @OneToMany(mappedBy = "partie", fetch = FetchType.EAGER)
    private List<ScorePartieKahootJoueur> scores;
}
