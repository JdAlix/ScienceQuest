package fr.iut.sciencequest.sae.exceptions.notFound;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PartieNotFoundException extends EntityNotFoundException{
    private static String entityName = "partie";
    public PartieNotFoundException(int id) {
        super(PartieNotFoundException.entityName, id);
    }

    public PartieNotFoundException(String field, Object value){
        super(PartieNotFoundException.entityName, field, value);
    }
}
