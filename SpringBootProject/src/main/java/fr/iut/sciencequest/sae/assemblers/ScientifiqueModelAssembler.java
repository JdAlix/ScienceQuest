package fr.iut.sciencequest.sae.assemblers;

import fr.iut.sciencequest.sae.controllers.ScientifiqueController;
import fr.iut.sciencequest.sae.dto.ScientifiqueDTO;
import fr.iut.sciencequest.sae.entities.scientifique.Scientifique;
import jakarta.annotation.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ScientifiqueModelAssembler extends RepresentationModelAssemblerSupport<Scientifique, ScientifiqueDTO> {
    public ScientifiqueModelAssembler() {
        super(ScientifiqueController.class, ScientifiqueDTO.class);
    }

    @Override
    @NonNull
    public ScientifiqueDTO toModel(@Nullable Scientifique entity) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(entity, ScientifiqueDTO.class);
    }
}
