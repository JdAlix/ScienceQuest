package fr.iut.sciencequest.sae.exceptions.notFound;

public class ThematiqueNotFoundException extends EntityNotFoundException{
    public ThematiqueNotFoundException(int id) {
        super("thématique", id);
    }
}
