package com.alpha.td_spring_boot_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    private Long id;
    private Customer customer;
    private Room room;
    private LocalDate bookingDate;
    private String description;
}
