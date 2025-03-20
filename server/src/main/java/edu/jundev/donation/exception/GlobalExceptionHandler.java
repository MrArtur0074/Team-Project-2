package edu.jundev.donation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNotFoundException(NotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(EmailExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleEmailExistsException(EmailExistsException e) {
        return e.getMessage();
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String constraintViolationExceptionHandler(BindException e) {
        return Objects.requireNonNull(e.getBindingResult()
                .getFieldError())
                .getField()
                + " "
                + e.getBindingResult()
                .getFieldError()
                .getDefaultMessage();
    }

    @ExceptionHandler(FileException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleFileException(FileException e) {
        return e.getMessage();
    }

    @ExceptionHandler(FileWriteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleFileWriteException(FileWriteException e) {
        return e.getMessage();
    }

    @ExceptionHandler(GCPFileUploadException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleGCPException(GCPFileUploadException e) {
        return e.getMessage();
    }

    @ExceptionHandler(ActivationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleActivationException(ActivationException e) {
        return e.getMessage();
    }


}
