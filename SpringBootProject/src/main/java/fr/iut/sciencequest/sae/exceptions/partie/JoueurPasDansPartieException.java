package fr.iut.sciencequest.sae.exceptions.partie;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class JoueurPasDansPartieException extends RuntimeException {
    public JoueurPasDansPartieException() {
        super("Le joueur n'est pas présent dans la partie");
    }
}
