package fr.iut.sciencequest.sae.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicatedIdException extends RuntimeException{
    public DuplicatedIdException(){
        super("duplicated id");
    }
}
