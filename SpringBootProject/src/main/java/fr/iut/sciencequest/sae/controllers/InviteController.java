package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.dto.invite.InviteWithPseudoOnlyDTO;
import fr.iut.sciencequest.sae.services.InviteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/invite")
public class InviteController {
    private final InviteService inviteService;
}
