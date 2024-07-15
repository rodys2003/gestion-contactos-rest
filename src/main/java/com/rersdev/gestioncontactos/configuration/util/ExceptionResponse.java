package com.rersdev.gestioncontactos.configuration.util;

import com.rersdev.gestioncontactos.configuration.exception.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;

@Component
public class ExceptionResponse {

    public Object createResponse (Exception exception, HttpServletRequest request, String message) {
        return ApiError.builder()
                .backMessage(exception.getLocalizedMessage())
                .message(message)
                .url(request.getRequestURL().toString())
                .method(request.getMethod())
                .timestamp(LocalDateTime.now())
                .build();
    }

    public Object createResponse (Exception exception, HttpServletRequest request) {
        return ApiError.builder()
                .backMessage(exception.getLocalizedMessage())
                .message(exception.getMessage())
                .url(request.getRequestURL().toString())
                .method(request.getMethod())
                .timestamp(LocalDateTime.now())
                .build();
    }

    public Object createResponse (MethodArgumentNotValidException exception, HttpServletRequest request) {
        return ApiError.builder()
                .backMessage(exception.getLocalizedMessage())
                .message(exception.getAllErrors().stream().map(each -> each.getDefaultMessage()).toList().toString())
                .url(request.getRequestURL().toString())
                .method(request.getMethod())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
