package fr.iut.sciencequest.sae.repositories;

import fr.iut.sciencequest.sae.entities.PartieKahoot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartieKahootRepository extends CrudRepository<PartieKahoot, Integer> {
    Optional<PartieKahoot> getPartieByCodeInvitation(String codeInvitation);
}
