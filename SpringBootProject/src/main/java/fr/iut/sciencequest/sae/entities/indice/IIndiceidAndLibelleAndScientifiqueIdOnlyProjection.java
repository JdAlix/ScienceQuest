package fr.iut.sciencequest.sae.entities.indice;

import fr.iut.sciencequest.sae.entities.scientifique.IScientifiqueIdOnlyProjection;

public interface IIndiceidAndLibelleAndScientifiqueIdOnlyProjection {
    int getId();
    String getLibelle();

    IScientifiqueIdOnlyProjection getScientifique();
}
