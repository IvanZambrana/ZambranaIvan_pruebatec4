package com.izambrana.pruebatec4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightBookingRequestDTO {
    private String origin;
    private String destination;
    private LocalDate date;
    private int peopleQ;
    private String seatType;
}
