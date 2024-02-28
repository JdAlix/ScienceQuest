package fr.iut.sciencequest.sae.services;


import fr.iut.sciencequest.sae.entities.Thematique;

public interface ThematiqueService {
    public Thematique update(Thematique thematique);

    public Thematique create(Thematique thematique);

    public Iterable<Thematique> findAll();
}
