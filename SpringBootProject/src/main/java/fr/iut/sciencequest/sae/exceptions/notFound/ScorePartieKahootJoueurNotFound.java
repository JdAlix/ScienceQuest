package fr.iut.sciencequest.sae.exceptions.notFound;

public class ScorePartieKahootJoueurNotFound extends EntityNotFoundException{
    public ScorePartieKahootJoueurNotFound(int id) {
        super("ScorePartieKahootJoueur", id);
    }
}
