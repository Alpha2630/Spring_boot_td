package com.alpha.td_spring_boot_api.exception;



import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RoomAlreadyBookedException.class)
    public ResponseEntity<Map<String, String>> handleRoomAlreadyBooked(RoomAlreadyBookedException e) {
        log.error("Gestion de l'exception RoomAlreadyBooked");
        Map<String, String> response = new HashMap<>();
        response.put("error", e.getMessage());
        response.put("status", HttpStatus.CONFLICT.toString());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(InvalidRoomNumberException.class)
    public ResponseEntity<Map<String, String>> handleInvalidRoomNumber(InvalidRoomNumberException e) {
        log.error("Gestion de l'exception InvalidRoomNumber");
        Map<String, String> response = new HashMap<>();
        response.put("error", e.getMessage());
        response.put("status", HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception e) {
        log.error("Erreur inattendue: {}", e.getMessage());
        Map<String, String> response = new HashMap<>();
        response.put("error", "Une erreur inattendue s'est produite");
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
