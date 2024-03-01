package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.ApplicationConfig;
import fr.iut.sciencequest.sae.assemblers.IndiceModelAssembler;
import fr.iut.sciencequest.sae.assemblers.ScientifiqueModelAssembler;
import fr.iut.sciencequest.sae.dto.indice.IndiceLibelleOnlyDTO;
import fr.iut.sciencequest.sae.dto.indice.IndiceSimpleWithScientifiquesIdDTO;
import fr.iut.sciencequest.sae.dto.scientifique.ScientifiqueDTO;
import fr.iut.sciencequest.sae.entities.Indice;
import fr.iut.sciencequest.sae.entities.Scientifique;
import fr.iut.sciencequest.sae.services.IndiceService;
import fr.iut.sciencequest.sae.services.interfaces.IScientifiqueService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/scientifiques")
public class ScientifiqueController {

    private final IScientifiqueService scientifiqueService;
    private final ScientifiqueModelAssembler scientifiqueModelAssembler;
    private final IndiceModelAssembler indiceModelAssembler;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private final PagedResourcesAssembler<Scientifique> pagedResourcesAssembler;
    private final IndiceService indiceService;
    private final ModelMapper modelMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedModel<ScientifiqueDTO> getAllScientists(@PageableDefault(size = ApplicationConfig.DEFAULT_PAGEABLE_SIZE) Pageable p,
                                                        @RequestParam(value = "thematiqueId", defaultValue = "") Integer thematiqueId,
                                                        @RequestParam(value = "difficulteId", defaultValue = "") Integer difficulteId) {

        Page<Scientifique> page = (thematiqueId == null && difficulteId == null ? this.scientifiqueService.findAll(p) : this.scientifiqueService.findAllWithCriteria(p, thematiqueId, difficulteId));
        return pagedResourcesAssembler.toModel(page, scientifiqueModelAssembler);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ScientifiqueDTO getScientistById(@PathVariable Integer id) {
        return scientifiqueModelAssembler.toModel(this.scientifiqueService.findById(id));
    }

    @GetMapping(value="/{id}/indices", produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<IndiceSimpleWithScientifiquesIdDTO> getScientistHints(@PathVariable Integer id) {
        return indiceModelAssembler.toCollectionModel(this.scientifiqueService.getLinkedIndicesByScientifiqueId(id));
    }

    @PostMapping(value="/{id}/indices", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public IndiceSimpleWithScientifiquesIdDTO postIndice(@PathVariable Integer id, @Valid @RequestBody IndiceLibelleOnlyDTO indiceWithLibelleOnly) {
        Indice indice = this.modelMapper.map(indiceWithLibelleOnly, Indice.class);
        indice.setScientifique(this.scientifiqueService.findById(id));
        return this.modelMapper.map(this.indiceService.create(indice), IndiceSimpleWithScientifiquesIdDTO.class);
    }
}
