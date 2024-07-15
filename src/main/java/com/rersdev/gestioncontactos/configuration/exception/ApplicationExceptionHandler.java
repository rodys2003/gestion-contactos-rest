package com.rersdev.gestioncontactos.configuration.exception;

import com.rersdev.gestioncontactos.configuration.exception.contact.ContactNotFoundException;
import com.rersdev.gestioncontactos.configuration.exception.contact.ImageStorageException;
import com.rersdev.gestioncontactos.configuration.exception.contact.PhoneAlreadyExistException;
import com.rersdev.gestioncontactos.configuration.util.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.NoSuchFileException;

@RequiredArgsConstructor

@RestControllerAdvice
public class ApplicationExceptionHandler {

    private final ExceptionResponse exceptionResponse;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(Exception exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionResponse.createResponse(exception,request,"An unexpected error occurred. We are working to resolve the issue."));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exceptionResponse.createResponse(exception,request));
    }

    @ExceptionHandler(NoSuchFileException.class)
    public ResponseEntity<Object> noSuchFileException(NoSuchFileException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exceptionResponse.createResponse(exception,request,"File not found"));
    }

    @ExceptionHandler(ContactNotFoundException.class)
    public ResponseEntity<Object> contactNotFoundException(ContactNotFoundException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exceptionResponse.createResponse(exception,request));
    }

    @ExceptionHandler(PhoneAlreadyExistException.class)
    public ResponseEntity<Object> phoneAlreadyExistException(PhoneAlreadyExistException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(exceptionResponse.createResponse(exception,request));
    }

    @ExceptionHandler(ImageStorageException.class)
    public ResponseEntity<Object> imageStorageException(ImageStorageException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exceptionResponse.createResponse(exception,request));
    }
}
