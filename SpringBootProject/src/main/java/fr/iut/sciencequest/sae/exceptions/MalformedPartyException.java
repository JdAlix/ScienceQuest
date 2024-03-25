package fr.iut.sciencequest.sae.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class MalformedPartyException extends RuntimeException {
    public MalformedPartyException(String message) {
        super(message);
    }
    public MalformedPartyException() {
        super("Error while creating party");
    }
}
