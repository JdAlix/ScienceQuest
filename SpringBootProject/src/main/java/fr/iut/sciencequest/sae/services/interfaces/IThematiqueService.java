package fr.iut.sciencequest.sae.services.interfaces;


import fr.iut.sciencequest.sae.entities.Thematique;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IThematiqueService {
    Thematique update(Thematique thematique);

    Thematique create(Thematique thematique);

    Page<Thematique> findAll(Pageable p);
}
