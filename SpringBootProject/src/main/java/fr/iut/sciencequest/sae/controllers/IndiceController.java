package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.entities.indice.Indice;
import fr.iut.sciencequest.sae.services.IndiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/indices")
public class IndiceController {

    private final IndiceService indiceService;

    public IndiceController(IndiceService indiceService) {
        this.indiceService = indiceService;
    }

    @PatchMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Indice patchIndice(@PathVariable("id") int id, @RequestBody Indice indice){
        indice.setId(id);
        return this.indiceService.update(indice);
    }
}
