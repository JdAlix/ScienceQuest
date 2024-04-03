package fr.iut.sciencequest.sae.controllers;

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

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public InviteSimpleDTO getInvite(@PathVariable int id){
        return this.modelMapper.map(this.inviteService.findById(id), InviteSimpleDTO.class);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public InviteSimpleDTO updateInvite(@PathVariable int id, @RequestBody @Valid InviteWithPseudoOnlyDTO updatedInvite){
        Invite invite = this.inviteService.findById(id);
        invite.setPseudo(updatedInvite.getPseudo());
        return this.modelMapper.map(this.inviteService.update(invite), InviteSimpleDTO.class);
    }
}
