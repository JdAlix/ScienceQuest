package fr.iut.sciencequest.sae.repositories;

import fr.iut.sciencequest.sae.entities.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer> {}
