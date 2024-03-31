package fr.iut.sciencequest.sae.exceptions.partie;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class ReponseTempsLimiteDepasseException extends RuntimeException {
    public ReponseTempsLimiteDepasseException() {
        super("La réponse a été donné trop tard");
    }
}
