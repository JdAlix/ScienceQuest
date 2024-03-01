package fr.iut.sciencequest.sae.assemblers;


import fr.iut.sciencequest.sae.controllers.ScientifiqueController;
import org.springframework.hateoas.CollectionModel;
import fr.iut.sciencequest.sae.dto.indice.IndiceSimpleWithScientifiquesIdDTO;
import fr.iut.sciencequest.sae.entities.Indice;
import jakarta.annotation.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class IndiceModelAssembler extends RepresentationModelAssemblerSupport<Indice, IndiceSimpleWithScientifiquesIdDTO> {
    public IndiceModelAssembler() {
        super(Indice.class, IndiceSimpleWithScientifiquesIdDTO.class);
    }

    @Override
    @NonNull
    public IndiceSimpleWithScientifiquesIdDTO toModel(@Nullable Indice entity) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(entity, IndiceSimpleWithScientifiquesIdDTO.class);
    }

    @Override
    @NonNull
    public CollectionModel<IndiceSimpleWithScientifiquesIdDTO> toCollectionModel(@Nullable Iterable<? extends Indice> entities) {
        assert entities != null;
        CollectionModel<IndiceSimpleWithScientifiquesIdDTO> collectionModel = super.toCollectionModel(entities);
        // Le lien n'est pas ajouté automatiquement si l'on utilise pas la pagination /!\
        if(collectionModel.iterator().hasNext())
            collectionModel.add(linkTo(methodOn(ScientifiqueController.class).getScientistHints(collectionModel.iterator().next().getScientifique().getId())).withSelfRel());

        return collectionModel;
    }

}
