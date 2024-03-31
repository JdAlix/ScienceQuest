package fr.iut.sciencequest.sae.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="questionpartiekahoot")
public class QuestionPartieKahoot {
    @EmbeddedId
    private QuestionPartieKahootKey id = new QuestionPartieKahootKey();

    @JsonBackReference
    @ManyToOne
    @MapsId("idQuestion")
    @JoinColumn(name = "idquestion")
    private Question question;

    @JsonBackReference
    @ManyToOne
    @MapsId("idPartieKahoot")
    @JoinColumn(name="idpartiekahoot")
    private PartieKahoot partie;

    @Column(name = "aetepose")
    private boolean aEtePose = false;
}
