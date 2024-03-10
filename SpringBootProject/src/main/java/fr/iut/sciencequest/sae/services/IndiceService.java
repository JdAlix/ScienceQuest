package fr.iut.sciencequest.sae.services;

import fr.iut.sciencequest.sae.entities.Indice;
import fr.iut.sciencequest.sae.exceptions.DuplicatedIdException;
import fr.iut.sciencequest.sae.exceptions.notFound.IndiceNotFoundException;
import fr.iut.sciencequest.sae.repositories.IndiceRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class IndiceService {

    private final IndiceRepository indiceRepository;

    public IndiceService(IndiceRepository indiceRepository){
        this.indiceRepository = indiceRepository;
    }

    public Iterable<Indice> findByScientifiqueId(int id) {
        return this.indiceRepository.findByScientifiqueId(id);
    }

    public Indice update(Indice indice){
        if(!this.indiceRepository.existsById(indice.getId())){
            throw new IndiceNotFoundException(indice.getId());
        }
        return this.indiceRepository.save(indice);
    }

    public Indice create(Indice indice){
        if(indice.getId() != null && this.indiceRepository.existsById(indice.getId())){
            throw new DuplicatedIdException();
        }
        return this.indiceRepository.save(indice);
    }

    public Indice patch(Indice incompleteIndice, Indice destIndice) throws IllegalAccessException {
        Class<?> indiceClass = Indice.class;
        Field[] indiceFields = indiceClass.getDeclaredFields();
        for(Field field: indiceFields){
            field.setAccessible(true);
            Object value = field.get(incompleteIndice);
            if(value != null){
                field.set(destIndice, value);
            }
            field.setAccessible(false);
        }
        return destIndice;
    }
}
