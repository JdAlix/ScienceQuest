package fr.iut.sciencequest.sae.services.interfaces;

import fr.iut.sciencequest.sae.entities.indice.IIndiceidAndLibelleOnlyProjection;
import fr.iut.sciencequest.sae.entities.indice.Indice;

public interface IIndiceService {
    Iterable<IIndiceidAndLibelleOnlyProjection> findByScientifiqueId(int id);
    Indice update(Indice indice);
    Indice create(Indice indice);
}
