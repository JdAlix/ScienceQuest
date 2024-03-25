package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.controllers.request.InviteRequest;
import fr.iut.sciencequest.sae.services.InviteService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/invite")
public class InviteController {
    private final InviteService inviteService;

    @GetMapping(value = "/{invite}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, Object> getParty(@PathVariable String invite, @RequestBody InviteRequest inviteRequest) {
        return this.inviteService.findByInvite(invite, inviteRequest.getPseudo());
    }
}
