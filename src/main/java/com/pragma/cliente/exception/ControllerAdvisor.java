package com.pragma.cliente.exception;

import com.pragma.cliente.util.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class ControllerAdvisor  {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(
            ResourceNotFoundException ex, WebRequest request) {

        return new ResponseEntity<>(new Message(HttpStatus.NOT_FOUND,"No hay registros", "No se encontraron registros"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> globalExceptionHandler(Exception ex, WebRequest request) {

        return new ResponseEntity<>(new Message(HttpStatus.INTERNAL_SERVER_ERROR,"Error interno", ex.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
