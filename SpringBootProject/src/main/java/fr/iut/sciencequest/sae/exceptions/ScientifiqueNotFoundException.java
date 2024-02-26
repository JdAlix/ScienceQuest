package fr.iut.sciencequest.sae.exceptions;

public class ScientifiqueNotFoundException extends RuntimeException {
    public final String uri;

    public ScientifiqueNotFoundException(String uri, String exception) {
        super(exception);
        this.uri = uri;
    }
}
