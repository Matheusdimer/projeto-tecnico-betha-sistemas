package com.betha.manutencao.controllers.exceptions;

import com.betha.manutencao.services.exceptions.DataIntegrityException;
import com.betha.manutencao.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validationError(MethodArgumentNotValidException e, HttpServletRequest request) {
        ValidationError error = new ValidationError(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação",
                "Houveram erros em um ou mais campos",
                request.getRequestURI()
        );

        for (FieldError err : e.getFieldErrors()) {
            error.addFieldError(err.getField(), err.getDefaultMessage());
        }

        return ResponseEntity.unprocessableEntity().body(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationError> validationError(ConstraintViolationException e, HttpServletRequest request) {
        ValidationError error = new ValidationError(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação",
                e.getMessage(),
                request.getRequestURI()
        );

        for (ConstraintViolation<?> err : e.getConstraintViolations()) {
            error.addFieldError(err.getPropertyPath().toString(), err.getMessage());
        }

        return ResponseEntity.unprocessableEntity().body(error);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        StandardError error = new StandardError(
                HttpStatus.NOT_FOUND.value(),
                "Entidade não encontrada",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrityError(DataIntegrityException e, HttpServletRequest request) {
        StandardError error = new StandardError(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de integridade de dados",
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.badRequest().body(error);
    }
}
