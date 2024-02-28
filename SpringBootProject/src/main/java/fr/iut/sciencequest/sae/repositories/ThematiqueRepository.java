package fr.iut.sciencequest.sae.repositories;

import fr.iut.sciencequest.sae.entities.Thematique;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ThematiqueRepository extends CrudRepository<Thematique, Integer> {
    public boolean existsByLibelle(String libelle);
}
