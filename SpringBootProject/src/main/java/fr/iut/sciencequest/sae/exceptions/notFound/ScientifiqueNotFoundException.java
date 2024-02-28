package fr.iut.sciencequest.sae.exceptions.notFound;

public class ScientifiqueNotFoundException extends EntityNotFoundException {

    public ScientifiqueNotFoundException(int id) {
        super("scientifique", id);
    }
}
