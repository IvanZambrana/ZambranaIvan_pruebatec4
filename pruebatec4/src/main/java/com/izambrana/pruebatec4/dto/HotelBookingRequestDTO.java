package com.izambrana.pruebatec4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelBookingRequestDTO {
    private String hotelCode;
    private LocalDate startDate;
    private LocalDate endDate;
    private int peopleQ;
    private String roomType;
    private List<GuestDTO> guests;

}
