package fr.iut.sciencequest.sae.controllers;

import fr.iut.sciencequest.sae.dto.invite.InviteDTO;
import fr.iut.sciencequest.sae.dto.invite.InviteSimpleDTO;
import fr.iut.sciencequest.sae.dto.invite.InviteWithPseudoOnlyDTO;
import fr.iut.sciencequest.sae.entities.Invite;
import fr.iut.sciencequest.sae.services.InviteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/invite")
public class InviteController {
    private final InviteService inviteService;
    private final ModelMapper modelMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public InviteSimpleDTO createInvite(@RequestBody @Valid InviteWithPseudoOnlyDTO inviteRequest){
        Invite invite = this.modelMapper.map(inviteRequest, Invite.class);
        return this.modelMapper.map(this.inviteService.create(invite), InviteSimpleDTO.class);
    }
}
