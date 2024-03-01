package fr.iut.sciencequest.sae.assemblers;

import fr.iut.sciencequest.sae.controllers.ScientifiqueController;
import fr.iut.sciencequest.sae.dto.scientifique.ScientifiqueDTO;
import fr.iut.sciencequest.sae.entities.Scientifique;
import jakarta.annotation.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ScientifiqueModelAssembler extends RepresentationModelAssemblerSupport<Scientifique, ScientifiqueDTO> {
    public ScientifiqueModelAssembler() {
        super(ScientifiqueController.class, ScientifiqueDTO.class);
    }

    @Override
    @NonNull
    public ScientifiqueDTO toModel(@Nullable Scientifique entity) {
        ModelMapper mapper = new ModelMapper();
        ScientifiqueDTO scientifiqueDTO = mapper.map(entity, ScientifiqueDTO.class);
        scientifiqueDTO.add(linkTo(methodOn(ScientifiqueController.class).getScientistHints(scientifiqueDTO.getId())).withRel("indices"));
        scientifiqueDTO.add(linkTo(methodOn(ScientifiqueController.class).getScientistById(scientifiqueDTO.getId())).withSelfRel());
        return scientifiqueDTO;
    }
}
