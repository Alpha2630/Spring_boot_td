package com.alpha.td_spring_boot_api.controller;



import com.alpha.td_spring_boot_api.model.*;
import com.alpha.td_spring_boot_api.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        log.info("GET /booking - Récupération de toutes les réservations");
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @PostMapping
    public ResponseEntity<List<Booking>> createBooking(@RequestBody BookingRequest request) {
        log.info("POST /booking - Création d'une nouvelle réservation");
        bookingService.createBooking(request);
        return ResponseEntity.ok(bookingService.getAllBookings());
    }
}