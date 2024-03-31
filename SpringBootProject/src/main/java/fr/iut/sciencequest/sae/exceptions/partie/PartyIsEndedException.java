package fr.iut.sciencequest.sae.exceptions.partie;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class PartyIsEndedException extends RuntimeException {
    public PartyIsEndedException() {
        super("Party is ended");
    }
}
