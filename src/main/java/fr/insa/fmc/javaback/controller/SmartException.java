package fr.insa.fmc.javaback.controller;


import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import fr.insa.fmc.javaback.Exception.ExceptionMessage;
import org.omg.IOP.ExceptionDetailMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RestControllerAdvice
public class SmartException {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionMessage> nullPointerExceptionHandler(HttpServletRequest request, NullPointerException exception) {

        ExceptionMessage message = new ExceptionMessage((LocalDateTime.now().format(formatter)), (request.getRequestURI().toString() + "?" + request.getQueryString())
                , (exception.getClass().getName())
                ,("Message d'erreur d'ali à avoir"));
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
