package fr.iut.sciencequest.sae.services.interfaces;

import fr.iut.sciencequest.sae.entities.Scientifique;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.util.Optional;

public interface IScientifiqueService {
    Scientifique update(Scientifique scientifique);

    Scientifique create(Scientifique scientifique);

    CollectionModel<EntityModel<Scientifique>> findAll(Integer page);

    EntityModel<Optional<Scientifique>> findById(int id);
}
