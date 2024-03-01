package fr.iut.sciencequest.sae.services.interfaces;

import fr.iut.sciencequest.sae.entities.Indice;

public interface IIndiceService {
    Iterable<Indice> findByScientifiqueId(int id);
    Indice update(Indice indice);
    Indice create(Indice indice);
}
