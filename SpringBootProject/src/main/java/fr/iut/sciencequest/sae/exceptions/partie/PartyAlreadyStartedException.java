package fr.iut.sciencequest.sae.exceptions.partie;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class PartyAlreadyStartedException extends RuntimeException {
    public PartyAlreadyStartedException() {
        super("Party already started");
    }
}
