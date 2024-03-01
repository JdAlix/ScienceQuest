package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.dto.indice.IndiceDTO;
import fr.iut.sciencequest.sae.dto.indice.IndiceSimpleWithScientifiquesIdDTO;
import fr.iut.sciencequest.sae.dto.indice.IndiceWithoutIdAndScientifiqueIdOnlyForPatchDTO;
import fr.iut.sciencequest.sae.entities.Indice;
import fr.iut.sciencequest.sae.repositories.IndiceRepository;
import fr.iut.sciencequest.sae.services.IndiceService;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/indices")
public class IndiceController {
    private final IndiceService indiceService;
    private final IndiceRepository indiceRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @PatchMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public IndiceSimpleWithScientifiquesIdDTO patchIndice(@PathVariable("id") int id, @RequestBody @Valid IndiceWithoutIdAndScientifiqueIdOnlyForPatchDTO indicePartial) throws NoSuchMethodException, MethodArgumentNotValidException {
        IndiceDTO indicedto = this.modelMapper.map(indicePartial, IndiceDTO.class);
        indicedto.setId(id);
        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(indicedto, "indice");;
        SpringValidatorAdapter adapter = new SpringValidatorAdapter(this.validator);
        adapter.validate(indicedto, errors);
        if(errors.hasErrors()){
            throw new MethodArgumentNotValidException(null, errors);        }


        Indice indice = this.modelMapper.map(indicedto, Indice.class);
        return this.modelMapper.map(this.indiceService.update(indice), IndiceSimpleWithScientifiquesIdDTO.class);
    }
}
