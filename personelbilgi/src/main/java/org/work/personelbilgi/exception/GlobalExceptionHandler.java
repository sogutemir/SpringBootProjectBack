package org.work.personelbilgi.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        logError(ex);
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), "Geçersiz istek");
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        logError(ex);
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), "Kaynak bulunamadı");
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception ex) {
        logError(ex);
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Bir hata oluştu", "Sunucu hatası");
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void logError(Exception ex) {
        logger.error("Hata oluştu: ", ex);
    }

    @Data
    @AllArgsConstructor
    private static class ErrorDetails {
        private Date timestamp;
        private String message;
        private String details;
    }
}
