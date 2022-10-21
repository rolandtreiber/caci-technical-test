package com.caciTechTest.app.exception.restErrorBody;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ApiValidationError {

    private HttpStatus status;
    private List<String> errors;

    public ApiValidationError(HttpStatus status, List<String> errors) {
        super();
        this.status = status;
        this.errors = errors;
    }

    public ApiValidationError(HttpStatus status, String error) {
        super();
        this.status = status;
        errors = Arrays.asList(error);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
