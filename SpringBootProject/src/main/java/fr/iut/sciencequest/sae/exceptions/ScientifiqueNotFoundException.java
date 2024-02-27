package fr.iut.sciencequest.sae.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ScientifiqueNotFoundException extends RuntimeException {

    public ScientifiqueNotFoundException(String message) {
        super(message);
    }
}
