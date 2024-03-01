package fr.iut.sciencequest.sae.repositories;

import fr.iut.sciencequest.sae.entities.Difficulte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DifficulteRepository extends JpaRepository<Difficulte, Integer> {
    Difficulte findDifficulteByLibelleEqualsIgnoreCase(String libelle);
}