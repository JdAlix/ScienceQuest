package fr.iut.sciencequest.sae.exceptions.partie;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class ReponseIncoherenteException extends RuntimeException {
    public ReponseIncoherenteException() {
        super("La réponse ne correspond pas à la question en cours");
    }
}
