package fr.iut.sciencequest.sae.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="reponse")
public class Reponse{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String reponse;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idquestion", nullable = false)
    private Question question;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idscientifique", nullable = false)
    private Scientifique scientifique;
}
