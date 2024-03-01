package fr.iut.sciencequest.sae.assemblers;

import fr.iut.sciencequest.sae.dto.thematique.ThematiqueSimpleDTO;
import fr.iut.sciencequest.sae.entities.Thematique;
import jakarta.annotation.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ThematiqueSimpleModelAssembler extends RepresentationModelAssemblerSupport<Thematique, ThematiqueSimpleDTO> {
    public ThematiqueSimpleModelAssembler() {
        super(Thematique.class, ThematiqueSimpleDTO.class);
    }

    @Override
    @NonNull
    public ThematiqueSimpleDTO toModel(@Nullable Thematique entity) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(entity, ThematiqueSimpleDTO.class);
    }
}
