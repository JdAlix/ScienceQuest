package fr.iut.sciencequest.sae.services;

import fr.iut.sciencequest.sae.controllers.ScientifiqueController;
import fr.iut.sciencequest.sae.entities.Scientifique;
import fr.iut.sciencequest.sae.exceptions.IncorrectPageException;
import fr.iut.sciencequest.sae.exceptions.ScientifiqueNotFoundException;
import fr.iut.sciencequest.sae.repositories.IndiceRepository;
import fr.iut.sciencequest.sae.repositories.ScientifiqueRepository;
import fr.iut.sciencequest.sae.services.interfaces.IScientifiqueService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ScientifiqueService implements IScientifiqueService {

    private static final int PAGE_SIZE = 1;

    private final ScientifiqueRepository scientifiqueRepository;

    public ScientifiqueService(ScientifiqueRepository scientifiqueRepository) {
        this.scientifiqueRepository = scientifiqueRepository;
    }

    private <T> CollectionModel<EntityModel<T>> getPageableCollectionModel(Page<T> pagedResult, Integer page, Method method, Object... args) {
        List<EntityModel<T>> entities = pagedResult.map(EntityModel::of).toList();

        List<Object> selfObj = new ArrayList<>(List.of(args)); selfObj.add(page);
        Link selfLink = linkTo(ScientifiqueController.class, method, selfObj.toArray()).withSelfRel().expand(page);

        CollectionModel<EntityModel<T>> result = CollectionModel.of(entities, selfLink);

        if (pagedResult.hasPrevious()) {
            List<Object> previousObj = new ArrayList<>(List.of(args)); previousObj.add(pagedResult.previousPageable().getPageNumber());
            result.add(linkTo(ScientifiqueController.class, method, previousObj.toArray()).withRel("previous"));
        }

        if (pagedResult.hasNext()) {
            List<Object> nextObj = new ArrayList<>(List.of(args)); nextObj.add(pagedResult.nextPageable().getPageNumber());
            result.add(linkTo(ScientifiqueController.class, method, nextObj.toArray()).withRel("next"));
        }

        return result;
    }

    @Override
    public Scientifique update(Scientifique scientifique) {
        return null;
    }

    @Override
    public Scientifique create(Scientifique scientifique) {
        return null;
    }

    @Override
    public CollectionModel<EntityModel<Scientifique>> findAll(Integer page) {
        try {
            Pageable paging = PageRequest.of(page, PAGE_SIZE);
            Page<Scientifique> pagedResult = scientifiqueRepository.findAll(paging);

            return getPageableCollectionModel(pagedResult, page, ScientifiqueController.class.getMethod("getAllScientists", Optional.class));
        } catch (IllegalArgumentException e) {
            throw new IncorrectPageException("numéro de page incorrect");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EntityModel<Optional<Scientifique>> findById(int id) {
        Optional<Scientifique> scientifiqueOptional = this.scientifiqueRepository.findById(id);
        Scientifique scientifique = scientifiqueOptional.orElseThrow(() -> new ScientifiqueNotFoundException("Scientifique non trouvé avec l'ID : " + id));

        Link selfLink = linkTo(methodOn(ScientifiqueController.class).getScientistById(id)).withSelfRel();
        return EntityModel.of(Optional.ofNullable(scientifique), selfLink);
    }
}
