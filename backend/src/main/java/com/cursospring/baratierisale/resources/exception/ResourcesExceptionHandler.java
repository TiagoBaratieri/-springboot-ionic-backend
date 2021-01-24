package com.cursospring.baratierisale.resources.exception;

import com.cursospring.baratierisale.services.exceptions.DataIntegrityException;
import com.cursospring.baratierisale.services.exceptions.ObjectNotFoundException;
import com.cursospring.baratierisale.services.exceptions.StandarError;
import com.cursospring.baratierisale.services.exceptions.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourcesExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandarError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){

        StandarError err = new StandarError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandarError> DataIntegrity(DataIntegrityException e, HttpServletRequest request){

        StandarError err = new StandarError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandarError> Validation(MethodArgumentNotValidException e, HttpServletRequest request){

        ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(),
                "Validation error",System.currentTimeMillis());
        for(FieldError x : e.getBindingResult().getFieldErrors()){
            err.addError(x.getField(), x.getDefaultMessage());

        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

}
