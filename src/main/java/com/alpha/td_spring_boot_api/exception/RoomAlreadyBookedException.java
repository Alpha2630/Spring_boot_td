package com.alpha.td_spring_boot_api.exception;



import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoomAlreadyBookedException extends RuntimeException {
    public RoomAlreadyBookedException(String message) {
        super(message);
        log.error("RoomAlreadyBookedException: {}", message);
    }
}