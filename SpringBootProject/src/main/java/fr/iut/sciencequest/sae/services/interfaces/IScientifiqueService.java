package fr.iut.sciencequest.sae.services.interfaces;

import fr.iut.sciencequest.sae.entities.Scientifique;
import fr.iut.sciencequest.sae.entities.indice.IIndiceidAndLibelleOnlyProjection;
import org.springframework.data.domain.Page;

public interface IScientifiqueService {
    Scientifique update(Scientifique scientifique);

    Scientifique create(Scientifique scientifique);

    Page<Scientifique> findAll(Integer page);

    Scientifique findById(int id);

    Iterable<IIndiceidAndLibelleOnlyProjection> getLinkedIndicesByScientifiqueId(int id);
}
