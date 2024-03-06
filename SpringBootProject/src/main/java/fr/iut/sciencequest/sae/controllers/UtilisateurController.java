package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.dto.utilisateur.UtilisateurDTO;
import fr.iut.sciencequest.sae.dto.utilisateur.UtilisateurWithPasswordDTO;
import fr.iut.sciencequest.sae.services.interfaces.IUtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/utilisateur")
public class UtilisateurController {
    private final IUtilisateurService utilisateurService;

    @PostMapping
    @ResponseBody
    public UtilisateurDTO register(UtilisateurWithPasswordDTO user) {
        return utilisateurService.save(user);
    }

    @GetMapping("/connexion")
    @ResponseBody
    public UtilisateurDTO login(UtilisateurWithPasswordDTO user) {
        return utilisateurService.login(user);
    }

    /*@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UtilisateurDTO getUser(@PathVariable Integer id) {
        return scientifiqueModelAssembler.toModel(this.scientifiqueService.findById(id));
    }*/
}
