package fr.iut.sciencequest.sae.services;

import fr.iut.sciencequest.sae.entities.Difficulte;
import fr.iut.sciencequest.sae.entities.Scientifique;
import fr.iut.sciencequest.sae.entities.Indice;
import fr.iut.sciencequest.sae.entities.Thematique;
import fr.iut.sciencequest.sae.exceptions.notFound.DifficulteNotFoundException;
import fr.iut.sciencequest.sae.exceptions.notFound.ScientifiqueNotFoundException;
import fr.iut.sciencequest.sae.exceptions.notFound.ThematiqueNotFoundException;
import fr.iut.sciencequest.sae.repositories.DifficulteRepository;
import fr.iut.sciencequest.sae.repositories.ScientifiqueRepository;
import fr.iut.sciencequest.sae.repositories.ThematiqueRepository;
import fr.iut.sciencequest.sae.services.interfaces.IScientifiqueService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ScientifiqueService implements IScientifiqueService {
    private final ScientifiqueRepository scientifiqueRepository;
    private final ThematiqueRepository thematiqueRepository;
    private final DifficulteRepository difficulteRepository;
    private final IndiceService indiceService;


    @Override
    public Scientifique update(Scientifique scientifique) {
        return null;
    }

    @Override
    public Scientifique create(Scientifique scientifique) {
        return null;
    }

    @Override
    public Page<Scientifique> findAll(Pageable page) {
        return scientifiqueRepository.findAll(page);
    }

    public Page<Scientifique> findAllWithCriteria(Pageable page, Integer tId, Integer dId) {
        Thematique thematique = (tId != null ? thematiqueRepository.findById(tId).orElseThrow(() -> new ThematiqueNotFoundException(tId)) : null);
        Difficulte difficulte = (dId != null ? difficulteRepository.findById(dId).orElseThrow(() -> new DifficulteNotFoundException(dId)) : null);

        if (thematique != null && difficulte != null) {
            return scientifiqueRepository.findAllByThematiqueEqualsAndDifficulteEquals(thematique, difficulte, page);
        } else if (thematique != null) {
            return scientifiqueRepository.findAllByThematiqueEquals(thematique, page);
        } else if (difficulte != null) {
            return scientifiqueRepository.findAllByDifficulteEquals(difficulte, page);
        }

        return scientifiqueRepository.findAll(page);
    }

    @Override
    public Scientifique findById(int id) {
        return this.scientifiqueRepository.findById(id).orElseThrow(() -> new ScientifiqueNotFoundException(id));
    }

    @Override
    public Iterable<Indice> getLinkedIndicesByScientifiqueId(int id){
        if(!this.scientifiqueRepository.existsById(id)){
            throw new ScientifiqueNotFoundException(id);
        }
        return this.indiceService.findByScientifiqueId(id);
    }
}
