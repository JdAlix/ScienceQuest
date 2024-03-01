package fr.iut.sciencequest.sae.assemblers;

import fr.iut.sciencequest.sae.dto.difficulte.DifficulteDTO;
import fr.iut.sciencequest.sae.entities.Difficulte;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class DifficulteModelAssembler extends RepresentationModelAssemblerSupport<Difficulte, DifficulteDTO> {
    public DifficulteModelAssembler() {
        super(Difficulte.class, DifficulteDTO.class);
    }

    @Override
    @NonNull
    public DifficulteDTO toModel(@Nullable Difficulte entity) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(entity, DifficulteDTO.class);
    }

}
