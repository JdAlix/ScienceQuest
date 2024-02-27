package fr.iut.sciencequest.sae.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class IncorrectPageException extends RuntimeException {

    public IncorrectPageException(String exception) {
        super(exception);
    }
}
