package fr.iut.sciencequest.sae.exceptions.notFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class JeuNotFoundException extends EntityNotFoundException{
    public JeuNotFoundException(int id) {
        super("Jeu", id);
    }
}
