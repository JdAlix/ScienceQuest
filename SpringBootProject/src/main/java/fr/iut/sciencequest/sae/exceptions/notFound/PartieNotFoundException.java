package fr.iut.sciencequest.sae.exceptions.notFound;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PartieNotFoundException extends EntityNotFoundException{
    public PartieNotFoundException(String message, int id) {
        super(message, id);
    }
}
