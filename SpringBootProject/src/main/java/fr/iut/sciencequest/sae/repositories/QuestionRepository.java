package fr.iut.sciencequest.sae.repositories;

import fr.iut.sciencequest.sae.entities.Question;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuestionRepository extends PagingAndSortingRepository<Question, Integer> {}
