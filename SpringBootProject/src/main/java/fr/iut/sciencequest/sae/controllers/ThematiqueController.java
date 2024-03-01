package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.ApplicationConfig;
import fr.iut.sciencequest.sae.assemblers.ThematiqueModelAssembler;
import fr.iut.sciencequest.sae.assemblers.ThematiqueSimpleModelAssembler;
import fr.iut.sciencequest.sae.dto.thematique.ThematiqueLibelleOnlyDTO;
import fr.iut.sciencequest.sae.dto.thematique.ThematiqueSimpleDTO;
import fr.iut.sciencequest.sae.entities.Thematique;
import fr.iut.sciencequest.sae.services.interfaces.IThematiqueService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/thematiques")
public class ThematiqueController {
    private final IThematiqueService thematiqueService;
    private final ThematiqueModelAssembler thematiqueModelAssembler;
    private final ThematiqueSimpleModelAssembler thematiqueSimpleModelAssembler;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private final PagedResourcesAssembler<Thematique> pagedResourcesAssembler;
    private final ModelMapper modelMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public PagedModel<ThematiqueSimpleDTO> getAllThematiques(@PageableDefault(size = ApplicationConfig.DEFAULT_PAGEABLE_SIZE) Pageable p) {
        return pagedResourcesAssembler.toModel(this.thematiqueService.findAll(p), thematiqueSimpleModelAssembler);
    }

    //TODO : gestion des erreurs remontées par @Valid
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ThematiqueSimpleDTO createThematique(@Valid @RequestBody ThematiqueLibelleOnlyDTO thematique) {
        return thematiqueSimpleModelAssembler.toModel(this.thematiqueService.create(this.modelMapper.map(thematique, Thematique.class)));
    }

    @PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ThematiqueSimpleDTO updateThematique(@PathVariable("id") int id, @Valid @RequestBody ThematiqueLibelleOnlyDTO partialThematique) {
        Thematique thematique = this.modelMapper.map(partialThematique, Thematique.class);
        thematique.setId(id);
        return thematiqueSimpleModelAssembler.toModel(this.thematiqueService.update(thematique));
    }
}
