package fr.iut.sciencequest.sae.services.interfaces;

import fr.iut.sciencequest.sae.entities.Indice;
import fr.iut.sciencequest.sae.entities.Scientifique;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IScientifiqueService {
    Scientifique update(Scientifique scientifique);

    Scientifique create(Scientifique scientifique);

    Page<Scientifique> findAll(Pageable page);
    Page<Scientifique> findAllWithCriteria(Pageable page, Integer tId, Integer dId);

    Scientifique findById(int id);

    Iterable<Indice> getLinkedIndicesByScientifiqueId(int id);
}
