package fr.iut.sciencequest.sae.services.interfaces;

import fr.iut.sciencequest.sae.entities.scientifique.Scientifique;
import fr.iut.sciencequest.sae.entities.indice.IIndiceidAndLibelleAndScientifiqueIdOnlyProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IScientifiqueService {
    Scientifique update(Scientifique scientifique);

    Scientifique create(Scientifique scientifique);

    Page<Scientifique> findAll(Pageable page);

    Scientifique findById(int id);

    Iterable<IIndiceidAndLibelleAndScientifiqueIdOnlyProjection> getLinkedIndicesByScientifiqueId(int id);
}
