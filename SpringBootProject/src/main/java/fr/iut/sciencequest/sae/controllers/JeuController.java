package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.dto.jeu.JeuDTO;
import fr.iut.sciencequest.sae.entities.Jeu;
import fr.iut.sciencequest.sae.exceptions.notFound.JeuNotFoundException;
import fr.iut.sciencequest.sae.repositories.JeuRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/jeux")
public class JeuController {
    private final JeuRepository jeuRepository;
    private final ModelMapper modelMapper;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Jeu getJeu(@PathVariable int id) {
        return this.jeuRepository.findById(id).orElseThrow(() -> new JeuNotFoundException(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<JeuDTO> getJeux() {
        List<JeuDTO> jeuDTOList = new ArrayList<>();
        for(Jeu jeu : this.jeuRepository.findAll()) {
            jeuDTOList.add(this.modelMapper.map(jeu, JeuDTO.class).add(
                    linkTo(JeuController.class).slash(jeu.getId()).withRel("self")
            ));
        }
        return jeuDTOList;
    }
}
