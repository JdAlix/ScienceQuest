package fr.iut.sciencequest.sae.repositories;

import fr.iut.sciencequest.sae.entities.Invite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InviteRepository extends CrudRepository<Invite, Integer> {}