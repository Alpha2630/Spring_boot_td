package com.alpha.td_spring_boot_api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {
    private String customerName;
    private String customerPhone;
    private String customerEmail;
    private Integer roomNumber;
    private String roomDescription;
    private String bookingDate; // Format: dd/MM/yyyy
}
