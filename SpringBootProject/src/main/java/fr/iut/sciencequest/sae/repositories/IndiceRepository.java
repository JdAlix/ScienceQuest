package fr.iut.sciencequest.sae.repositories;

import fr.iut.sciencequest.sae.entities.indice.Indice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IndiceRepository extends PagingAndSortingRepository<Indice, Integer>, CrudRepository<Indice, Integer> {
    <T> Iterable<T> findByScientifiqueId(int id, Class<T> type);

    boolean existsByScientifiqueId(int id);
    Iterable<Indice> findByScientifiqueId(int id);
}