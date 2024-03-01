package fr.iut.sciencequest.sae.exceptions.notFound;

public class DifficulteNotFoundException extends EntityNotFoundException{
    public DifficulteNotFoundException(int id) {
        super("Difficulté", id);
    }
}
