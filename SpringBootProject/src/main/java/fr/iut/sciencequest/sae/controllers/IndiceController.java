package fr.iut.sciencequest.sae.controllers;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.iut.sciencequest.sae.dto.indice.IndiceSimpleWithScientifiquesIdDTO;
import fr.iut.sciencequest.sae.dto.indice.IndiceWithoutIdAndScientifiqueIdOnlyForPatchDTO;
import fr.iut.sciencequest.sae.entities.Indice;
import fr.iut.sciencequest.sae.repositories.IndiceRepository;
import fr.iut.sciencequest.sae.services.IndiceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/indices")
public class IndiceController extends Controller {
    private final IndiceService indiceService;
    private final ModelMapper modelMapper;

    @PatchMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public IndiceSimpleWithScientifiquesIdDTO patchIndice(@PathVariable("id") int id, @RequestBody @Valid IndiceWithoutIdAndScientifiqueIdOnlyForPatchDTO indiceInput) throws JsonMappingException {
        Indice indice = this.modelMapper.map(indiceInput, Indice.class);
        indice.setId(id);

        return this.modelMapper.map(this.indiceService.update(indice), IndiceSimpleWithScientifiquesIdDTO.class);
    }
}
