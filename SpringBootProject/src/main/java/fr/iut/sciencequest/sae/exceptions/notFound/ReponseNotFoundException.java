package fr.iut.sciencequest.sae.exceptions.notFound;

public class ReponseNotFoundException extends EntityNotFoundException{
    public ReponseNotFoundException(int id) {
        super("Reponse", id);
    }
}
