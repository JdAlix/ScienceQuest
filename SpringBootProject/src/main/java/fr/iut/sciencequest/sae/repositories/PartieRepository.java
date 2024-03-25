package fr.iut.sciencequest.sae.repositories;

import fr.iut.sciencequest.sae.entities.Partie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartieRepository extends CrudRepository<Partie, Integer> {
    Optional<Partie> getPartieByCodeInvitation(String codeInvitation);
}
