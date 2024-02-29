package fr.iut.sciencequest.sae.services;

import fr.iut.sciencequest.sae.entities.scientifique.Scientifique;
import fr.iut.sciencequest.sae.entities.indice.IIndiceidAndLibelleAndScientifiqueIdOnlyProjection;
import fr.iut.sciencequest.sae.exceptions.notFound.ScientifiqueNotFoundException;
import fr.iut.sciencequest.sae.repositories.ScientifiqueRepository;
import fr.iut.sciencequest.sae.services.interfaces.IScientifiqueService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ScientifiqueService implements IScientifiqueService {

    private static final int PAGE_SIZE = 1;

    private final ScientifiqueRepository scientifiqueRepository;
    private final IndiceService indiceService;

    public ScientifiqueService(ScientifiqueRepository scientifiqueRepository, IndiceService indiceService) {
        this.scientifiqueRepository = scientifiqueRepository;
        this.indiceService = indiceService;
    }

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

    @Override
    public Scientifique findById(int id) {
        return this.scientifiqueRepository.findById(id).orElseThrow(() -> new ScientifiqueNotFoundException(id));
    }

    @Override
    public Iterable<IIndiceidAndLibelleAndScientifiqueIdOnlyProjection> getLinkedIndicesByScientifiqueId(int id){
        if(!this.scientifiqueRepository.existsById(id)){
            throw new ScientifiqueNotFoundException(id);
        }
        return this.indiceService.findByScientifiqueId(id);
    }
}
