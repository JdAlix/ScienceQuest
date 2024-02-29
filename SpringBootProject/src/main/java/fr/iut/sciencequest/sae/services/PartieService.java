package fr.iut.sciencequest.sae.services;

import fr.iut.sciencequest.sae.entities.Partie;
import fr.iut.sciencequest.sae.exceptions.notFound.PartieNotFoundException;
import fr.iut.sciencequest.sae.repositories.PartieRepository;
import fr.iut.sciencequest.sae.services.interfaces.IPartieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PartieService implements IPartieService {
    private final PartieRepository partieRepository;

    public Partie findById(int id) {
        return this.partieRepository.findById(id).orElseThrow(() ->
                new PartieNotFoundException("Partie", id)
        );
    }
    public Partie save(Partie p) {
        return new Partie();
    }
}
