package fr.iut.sciencequest.sae.exceptions.advices;

import fr.iut.sciencequest.sae.exceptions.IncorrectPageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ScientifiqueNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(IncorrectPageException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Map<String, Object> exceptionHandler(IncorrectPageException ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", String.valueOf(new Timestamp(System.currentTimeMillis())));
        map.put("error", ex.getMessage());
        map.put("status", HttpStatus.NOT_FOUND.value());
        map.put("path", ex.uri);
        return map;
    }
}
