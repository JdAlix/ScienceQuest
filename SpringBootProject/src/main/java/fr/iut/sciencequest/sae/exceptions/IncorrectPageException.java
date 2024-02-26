package fr.iut.sciencequest.sae.exceptions;

public class IncorrectPageException extends RuntimeException {
    public final String uri;
    public IncorrectPageException(String uri, String exception) {
        super(exception);
        this.uri = uri;
    }
}
