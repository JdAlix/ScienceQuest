package fr.iut.sciencequest.sae.repositories;

import fr.iut.sciencequest.sae.entities.scientifique.Scientifique;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScientifiqueRepository extends PagingAndSortingRepository<Scientifique, Integer>, CrudRepository<Scientifique, Integer> {
}
