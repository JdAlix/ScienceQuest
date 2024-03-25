package fr.iut.sciencequest.sae.exceptions.notFound;

public class JeuNotFoundException extends EntityNotFoundException{
    public JeuNotFoundException(int id) {
        super("Jeu", id);
    }
}
