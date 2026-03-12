package com.alpha.td_spring_boot_api.service;



import com.alpha.td_spring_boot_api.model.*;
import com.alpha.td_spring_boot_api.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
public class BookingService {
    private final ConcurrentHashMap<Long, Booking> bookings = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public List<Booking> getAllBookings() {
        log.info("Récupération de toutes les réservations");
        return new ArrayList<>(bookings.values());
    }

    public Booking createBooking(BookingRequest request) {
        log.info("Tentative de création d'une réservation pour la chambre {} à la date {}",
                request.getRoomNumber(), request.getBookingDate());

        if (request.getRoomNumber() < 1 || request.getRoomNumber() > 9) {
            log.error("Numéro de chambre invalide: {}", request.getRoomNumber());
            throw new InvalidRoomNumberException("Room number must be between 1 and 9");
        }

        LocalDate bookingDate = LocalDate.parse(request.getBookingDate(), formatter);

        boolean isAvailable = bookings.values().stream()
                .noneMatch(booking ->
                        booking.getRoom().getRoomNumber().equals(request.getRoomNumber()) &&
                                booking.getBookingDate().equals(bookingDate)
                );

        if (!isAvailable) {
            log.error("Chambre {} déjà réservée pour le {}", request.getRoomNumber(), request.getBookingDate());
            throw new RoomAlreadyBookedException(
                    "Room " + request.getRoomNumber() + " is already booked for date " +
                            request.getBookingDate()
            );
        }

        Customer customer = new Customer(
                request.getCustomerName(),
                request.getCustomerPhone(),
                request.getCustomerEmail()
        );

        Room room = new Room(
                request.getRoomNumber(),
                request.getRoomDescription()
        );

        Booking booking = new Booking(
                idCounter.getAndIncrement(),
                customer,
                room,
                bookingDate,
                request.getRoomDescription()
        );

        bookings.put(booking.getId(), booking);
        log.info("Réservation créée avec succès. ID: {}", booking.getId());
        return booking;
    }
}