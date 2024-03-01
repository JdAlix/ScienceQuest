package fr.iut.sciencequest.sae.assemblers;

import fr.iut.sciencequest.sae.dto.thematique.ThematiqueDTO;
import fr.iut.sciencequest.sae.entities.Thematique;
import jakarta.annotation.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ThematiqueModelAssembler extends RepresentationModelAssemblerSupport<Thematique, ThematiqueDTO> {
    public ThematiqueModelAssembler() {
        super(Thematique.class, ThematiqueDTO.class);
    }

    @Override
    @NonNull
    public ThematiqueDTO toModel(@Nullable Thematique entity) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(entity, ThematiqueDTO.class);
    }
}
