package fr.iut.sciencequest.sae.services.interfaces;


import fr.iut.sciencequest.sae.entities.Thematique;

public interface IThematiqueService {
    Thematique update(Thematique thematique);

    Thematique create(Thematique thematique);

    Iterable<Thematique> findAll();
}
