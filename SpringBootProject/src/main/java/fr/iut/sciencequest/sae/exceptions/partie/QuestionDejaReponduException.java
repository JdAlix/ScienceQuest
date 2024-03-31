package fr.iut.sciencequest.sae.exceptions.partie;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class QuestionDejaReponduException extends RuntimeException {
    public QuestionDejaReponduException() {
        super("Le joueur a déjà répondu à la question");
    }
}
