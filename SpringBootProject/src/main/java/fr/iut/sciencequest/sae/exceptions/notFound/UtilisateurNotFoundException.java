package fr.iut.sciencequest.sae.exceptions.notFound;

public class UtilisateurNotFoundException extends EntityNotFoundException{
    public UtilisateurNotFoundException(Object id) {
        super("utilisateur", id);
    }
}
