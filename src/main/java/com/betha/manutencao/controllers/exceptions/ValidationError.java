package com.betha.manutencao.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
    private List<FieldErrorMessage> errors = new ArrayList<>();

    public ValidationError() {
    }

    public ValidationError(Integer status, String error, String message, String path) {
        super(status, error, message, path);
    }

    public List<FieldErrorMessage> getErrors() {
        return errors;
    }

    public void addFieldError(String field, String message) {
        this.errors.add(new FieldErrorMessage(field, message));
    }
}
