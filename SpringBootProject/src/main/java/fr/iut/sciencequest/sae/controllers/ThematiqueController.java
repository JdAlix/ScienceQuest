package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.entities.Thematique;
import fr.iut.sciencequest.sae.exceptions.DuplicatedEntity;
import fr.iut.sciencequest.sae.repositories.ThematiqueRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/thematiques")
public class ThematiqueController {

    private final ThematiqueRepository thematiqueRepository;

    public ThematiqueController(ThematiqueRepository thematiqueRepository) {
        this.thematiqueRepository = thematiqueRepository;
    }
/*
    @ExceptionHandler(ConstraintViolationException.class)
    public void t(ConstraintViolationException e){
        throw new RuntimeException();
    }*/

    @ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
    public void
    handleConstraintViolationException(ConstraintViolationException ex){
        System.out.println("o ?");
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Thematique> getAllThematiques() {
        return this.thematiqueRepository.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Thematique postThematique(@Valid @RequestBody Thematique thematique){
        thematique = this.thematiqueRepository.save(thematique);
        return thematique;
    }
}
