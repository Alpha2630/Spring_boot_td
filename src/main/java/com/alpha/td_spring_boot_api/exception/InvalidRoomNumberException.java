package com.alpha.td_spring_boot_api.exception;



import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidRoomNumberException extends RuntimeException {
    public InvalidRoomNumberException(String message) {
        super(message);
        log.error("InvalidRoomNumberException: {}", message);
    }
}
