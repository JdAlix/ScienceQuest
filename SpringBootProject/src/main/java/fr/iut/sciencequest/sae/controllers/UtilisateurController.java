package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.assemblers.ScientifiqueModelAssembler;
import fr.iut.sciencequest.sae.dto.utilisateur.UtilisateurDTO;
import fr.iut.sciencequest.sae.dto.utilisateur.UtilisateurWithPasswordDTO;
import fr.iut.sciencequest.sae.services.ScientifiqueService;
import fr.iut.sciencequest.sae.services.UtilisateurService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/utilisateur")
public class UtilisateurController {
    private final UtilisateurService utilisateurService;
    private final ModelMapper modelMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UtilisateurDTO register(@RequestBody UtilisateurWithPasswordDTO user) {
        return utilisateurService.save(user);
    }

    @PostMapping(path = "/connexion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UtilisateurDTO login(@RequestBody UtilisateurWithPasswordDTO user) {
        return utilisateurService.login(user);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UtilisateurDTO getUser(@PathVariable Integer id) {
        return modelMapper.map(this.utilisateurService.findUserById(id), UtilisateurDTO.class);
    }
}
