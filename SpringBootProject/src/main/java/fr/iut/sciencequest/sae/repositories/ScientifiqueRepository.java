package fr.iut.sciencequest.sae.repositories;

import fr.iut.sciencequest.sae.entities.Scientifique;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ScientifiqueRepository extends PagingAndSortingRepository<Scientifique, Integer>, CrudRepository<Scientifique, Integer> {}
