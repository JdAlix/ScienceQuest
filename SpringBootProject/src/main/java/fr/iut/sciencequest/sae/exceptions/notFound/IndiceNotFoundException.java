package fr.iut.sciencequest.sae.exceptions.notFound;

public class IndiceNotFoundException extends EntityNotFoundException{
    public IndiceNotFoundException(int id){
        super("indice", id);
    }
}
