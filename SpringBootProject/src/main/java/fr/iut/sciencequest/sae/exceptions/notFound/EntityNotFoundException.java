package fr.iut.sciencequest.sae.exceptions.notFound;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public abstract class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(){
        super("entity not found");
    }
    public EntityNotFoundException(String entityName, Object id){
        super(entityName + " not found with id : " + id);
    }

    public EntityNotFoundException(String entityName, String fieldName, Object value){
        super(entityName + " not found with " + fieldName + " : " + value);
    }
}
