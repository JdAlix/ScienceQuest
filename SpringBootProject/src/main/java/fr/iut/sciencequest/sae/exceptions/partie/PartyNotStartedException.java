package fr.iut.sciencequest.sae.exceptions.partie;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class PartyNotStartedException extends RuntimeException {
    public PartyNotStartedException() {
        super("Party not started");
    }
}
