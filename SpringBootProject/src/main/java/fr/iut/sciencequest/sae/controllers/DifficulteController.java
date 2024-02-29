package fr.iut.sciencequest.sae.controllers;


import fr.iut.sciencequest.sae.ApplicationConfig;
import fr.iut.sciencequest.sae.assemblers.DifficulteModelAssembler;
import fr.iut.sciencequest.sae.dto.difficulte.DifficulteDTO;
import fr.iut.sciencequest.sae.entities.Difficulte;
import fr.iut.sciencequest.sae.services.DifficulteService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/difficultes")
public class DifficulteController extends Controller {
    private final DifficulteModelAssembler difficulteModelAssembler;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private final PagedResourcesAssembler<Difficulte> pagedResourcesAssembler;
    public final DifficulteService difficulteService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public PagedModel<DifficulteDTO> getAllDifficultes(@PageableDefault(size = ApplicationConfig.DEFAULT_PAGEABLE_SIZE) Pageable p) {
        Page<Difficulte> difficultePage = this.difficulteService.findAll(p);
        return pagedResourcesAssembler.toModel(difficultePage, difficulteModelAssembler);
    }
}
