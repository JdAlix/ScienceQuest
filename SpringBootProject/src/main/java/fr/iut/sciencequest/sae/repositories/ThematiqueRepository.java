package fr.iut.sciencequest.sae.repositories;

import fr.iut.sciencequest.sae.entities.Thematique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ThematiqueRepository extends JpaRepository<Thematique, Integer> {
    boolean existsByLibelle(String libelle);
    Thematique findThematiqueByLibelleEqualsIgnoreCase(String thematique);
}
