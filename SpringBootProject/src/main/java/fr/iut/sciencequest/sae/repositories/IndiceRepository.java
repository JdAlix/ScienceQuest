package fr.iut.sciencequest.sae.repositories;

import fr.iut.sciencequest.sae.entities.IidAndLibelleOnly;
import fr.iut.sciencequest.sae.entities.Indice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndiceRepository extends PagingAndSortingRepository<Indice, Integer>, CrudRepository<Indice, Integer> {
    Iterable<IidAndLibelleOnly> findByScientifiqueId(int id);

}