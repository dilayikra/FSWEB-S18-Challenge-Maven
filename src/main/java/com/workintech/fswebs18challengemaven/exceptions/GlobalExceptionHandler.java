package com.workintech.fswebs18challengemaven.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j // Görev 3: Error logu basabilmek için bu şart!
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CardException.class)
    public ResponseEntity<CardErrorResponse> handleException(CardException exception) {
        // Ödev kuralı: Hata oluştuğunda konsola "ERROR" logu basıyoruz
        log.error("CardException occurred! Message: {}, Status: {}",
                exception.getMessage(), exception.getHttpStatus());

        CardErrorResponse response = new CardErrorResponse(exception.getMessage());
        return new ResponseEntity<>(response, exception.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CardErrorResponse> handleException(Exception exception) {
        // Beklenmedik sistem hataları için de log basalım
        log.error("An unexpected error occurred: {}", exception.getMessage());

        CardErrorResponse response = new CardErrorResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
