package fr.iut.sciencequest.sae.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static org.hibernate.internal.util.collections.CollectionHelper.listOf;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicatedFieldException extends RuntimeException {

    //TODO : retourner une erreur avec le msg + LA LISTE des fields concernés en format JSON
    public DuplicatedFieldException(String field){
        super("Unicity constraints broken on fields :" + listOf(field));
    }

    public DuplicatedFieldException(List<String> fields){
        super("Unicity constraints broken on fields :" + fields);
    }
}
