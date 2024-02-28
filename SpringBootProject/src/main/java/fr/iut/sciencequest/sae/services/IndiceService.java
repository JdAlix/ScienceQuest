package fr.iut.sciencequest.sae.services;

import fr.iut.sciencequest.sae.entities.indice.IIndiceidAndLibelleOnlyProjection;
import fr.iut.sciencequest.sae.entities.indice.Indice;
import fr.iut.sciencequest.sae.exceptions.DuplicatedIdException;
import fr.iut.sciencequest.sae.exceptions.notFound.IndiceNotFoundException;
import fr.iut.sciencequest.sae.repositories.IndiceRepository;
import fr.iut.sciencequest.sae.services.interfaces.IIndiceService;
import org.springframework.stereotype.Service;

@Service
public class IndiceService implements IIndiceService {

    private final IndiceRepository indiceRepository;

    public IndiceService(IndiceRepository indiceRepository){
        this.indiceRepository = indiceRepository;
    }

    @Override
    public Iterable<IIndiceidAndLibelleOnlyProjection> findByScientifiqueId(int id) {
        return this.indiceRepository.findByScientifiqueId(id, IIndiceidAndLibelleOnlyProjection.class);
    }

    @Override
    public Indice update(Indice indice){
        if(!this.indiceRepository.existsById(indice.getId())){
            throw new IndiceNotFoundException(indice.getId());
        }
        return this.indiceRepository.save(indice);
    }

    @Override
    public Indice create(Indice indice){
        if(this.indiceRepository.existsById(indice.getId())){
            throw new DuplicatedIdException();
        }
        return this.indiceRepository.save(indice);
    }
}
