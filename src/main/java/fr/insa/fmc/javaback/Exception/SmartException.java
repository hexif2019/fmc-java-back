package fr.insa.fmc.javaback.Exception;


import fr.insa.fmc.javaback.Exception.ExceptionMessage;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RestControllerAdvice
public class SmartException extends ResponseEntityExceptionHandler {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    @ResponseBody
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionMessage> nullPointerExceptionHandler(HttpServletRequest request, NullPointerException exception) {
        ExceptionMessage message = new ExceptionMessage("entité null",exception.getClass().getName(),request.getRequestURI().toString() ,LocalDateTime.now().format(formatter));
        ResponseEntity<ExceptionMessage> MessageDeRetour = new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
        return MessageDeRetour;
    }


}
