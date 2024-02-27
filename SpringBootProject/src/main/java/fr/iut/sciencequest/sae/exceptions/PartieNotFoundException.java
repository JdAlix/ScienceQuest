package fr.iut.sciencequest.sae.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PartieNotFoundException extends RuntimeException{
    public PartieNotFoundException(String message) {
        super(message);
    }
}
