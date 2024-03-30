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

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Questionpartiekahoot",
            joinColumns = @JoinColumn(name = "idpartiekahoot"),
            inverseJoinColumns = @JoinColumn(name="idquestion")
    )
    private List<Question> questions;
}
