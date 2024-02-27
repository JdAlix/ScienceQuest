package fr.iut.sciencequest.sae.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicatedEntity extends RuntimeException {
    public DuplicatedEntity(String message){
        super(message);
    }
}
