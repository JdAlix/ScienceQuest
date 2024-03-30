package fr.iut.sciencequest.sae.exceptions.notFound;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PartieKahootNotFoundException extends EntityNotFoundException{
    private static String entityName = "partieKahoot";
    public PartieKahootNotFoundException(int id) {
        super(PartieKahootNotFoundException.entityName, id);
    }

    public PartieKahootNotFoundException(String field, Object value){
        super(PartieKahootNotFoundException.entityName, field, value);
    }
}
