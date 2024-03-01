package fr.iut.sciencequest.sae.repositories;

import fr.iut.sciencequest.sae.entities.Difficulte;
import fr.iut.sciencequest.sae.entities.Scientifique;
import fr.iut.sciencequest.sae.entities.Thematique;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScientifiqueRepository extends JpaRepository<Scientifique, Integer> {
    Page<Scientifique> findAllByDifficulteEquals(Difficulte difficulte, Pageable pageable);
    Page<Scientifique> findAllByThematiqueEquals(Thematique thematique, Pageable pageable);
    Page<Scientifique> findAllByThematiqueEqualsAndDifficulteEquals(Thematique thematique, Difficulte difficulte, Pageable pageable);

}
