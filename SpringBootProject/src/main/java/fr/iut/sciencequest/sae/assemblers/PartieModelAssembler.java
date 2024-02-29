package fr.iut.sciencequest.sae.assemblers;

import fr.iut.sciencequest.sae.controllers.PartieController;
import fr.iut.sciencequest.sae.entities.Partie;
import fr.iut.sciencequest.sae.dto.PartieDTO;
import jakarta.annotation.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class PartieModelAssembler extends RepresentationModelAssemblerSupport<Partie, PartieDTO> {
    public PartieModelAssembler() {
        super(PartieController.class, PartieDTO.class);
    }

    @Override
    @NonNull
    public PartieDTO toModel(@Nullable Partie entity) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(entity, PartieDTO.class);
    }

}
