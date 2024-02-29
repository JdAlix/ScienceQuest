package fr.iut.sciencequest.sae.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="question")
public class Question extends BaseEntity {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String question;

    @NotEmpty
    @OneToMany(mappedBy = "id")
    @Fetch(FetchMode.JOIN) // Sinon crash (Could not write JSON: failed to lazily initialize a collection of role)
    private List<Reponse> reponses;
}

