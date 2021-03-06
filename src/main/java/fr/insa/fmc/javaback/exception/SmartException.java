package fr.insa.fmc.javaback.exception;


import com.paypal.base.rest.PayPalRESTException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.mail.AuthenticationFailedException;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RestControllerAdvice
public class SmartException extends ResponseEntityExceptionHandler {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @ResponseBody
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionMessage> nullPointerExceptionHandler(HttpServletRequest request, NullPointerException exception) {
        ExceptionMessage message = new ExceptionMessage(exception.getMessage(),exception.getClass().getName(),request.getRequestURI().toString() ,LocalDateTime.now().format(formatter),HttpStatus.INTERNAL_SERVER_ERROR.toString());
        ResponseEntity<ExceptionMessage> MessageDeRetour = new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
        return MessageDeRetour;
    }

    @ResponseBody
    @ExceptionHandler(WrongAddressException.class)
    public ResponseEntity<ExceptionMessage> wrongAddressExceptionHandler(HttpServletRequest request, WrongAddressException exception) {

        ExceptionMessage message = new ExceptionMessage(exception.getMessage(),exception.getClass().getName(),request.getRequestURI().toString() ,LocalDateTime.now().format(formatter),HttpStatus.BAD_REQUEST.toString());
        ResponseEntity<ExceptionMessage> MessageDeRetour = new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        return MessageDeRetour;
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionMessage> genericExceptionHandler(HttpServletRequest request, Exception exception) {
        ExceptionMessage message = new ExceptionMessage(exception.getMessage(),exception.getClass().getName(),request.getRequestURI().toString() ,LocalDateTime.now().format(formatter),HttpStatus.INTERNAL_SERVER_ERROR.toString());
        ResponseEntity<ExceptionMessage> MessageDeRetour = new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
        return MessageDeRetour;
    }

    @ResponseBody
    @ExceptionHandler(PayPalRESTException.class)
    public ResponseEntity<ExceptionMessage> PaypalExceptionHandler(HttpServletRequest request, Exception exception) {
        ExceptionMessage message = new ExceptionMessage(exception.getMessage(),exception.getClass().getName(),request.getRequestURI().toString() ,LocalDateTime.now().format(formatter),HttpStatus.INTERNAL_SERVER_ERROR.toString());
        ResponseEntity<ExceptionMessage> MessageDeRetour = new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
        return MessageDeRetour;
    }

    @ResponseBody
    @ExceptionHandler(SameEmailException.class)
    public ResponseEntity<ExceptionMessage> nullPointerExceptionHandler(HttpServletRequest request, SameEmailException exception) {
        ExceptionMessage message = new ExceptionMessage(exception.getMessage(),exception.getClass().getName(),request.getRequestURI().toString() ,LocalDateTime.now().format(formatter),HttpStatus.INTERNAL_SERVER_ERROR.toString());
        ResponseEntity<ExceptionMessage> MessageDeRetour = new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
        return MessageDeRetour;
    }
    @ResponseBody
    @ExceptionHandler(ParseException.class)
    public ResponseEntity<ExceptionMessage> parseExceptionHandler(HttpServletRequest request, ParseException exception) {
        ExceptionMessage message = new ExceptionMessage(exception.getMessage(),exception.getClass().getName(),request.getRequestURI().toString() ,LocalDateTime.now().format(formatter),HttpStatus.INTERNAL_SERVER_ERROR.toString());
        ResponseEntity<ExceptionMessage> MessageDeRetour = new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
        return MessageDeRetour;
    }

    @ResponseBody
    @ExceptionHandler(AuthenticationFailException.class)
    public ResponseEntity<ExceptionMessage> authenticationFailedException(HttpServletRequest request, AuthenticationFailException exception) {
        ExceptionMessage message = new ExceptionMessage(exception.getMessage(),exception.getClass().getName(), request.getRequestURI(),LocalDateTime.now().format(formatter),HttpStatus.UNAUTHORIZED.toString());
        ResponseEntity<ExceptionMessage> MessageDeRetour = new ResponseEntity<>(message,HttpStatus.UNAUTHORIZED);
        return MessageDeRetour;
    }


}
