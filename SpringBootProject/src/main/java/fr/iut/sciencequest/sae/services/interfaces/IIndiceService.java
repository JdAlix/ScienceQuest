package fr.iut.sciencequest.sae.services.interfaces;

import fr.iut.sciencequest.sae.entities.indice.IIndiceidAndLibelleAndScientifiqueIdOnlyProjection;
import fr.iut.sciencequest.sae.entities.indice.Indice;

public interface IIndiceService {
    Iterable<IIndiceidAndLibelleAndScientifiqueIdOnlyProjection> findByScientifiqueId(int id);
    Indice update(Indice indice);
    Indice create(Indice indice);
}
