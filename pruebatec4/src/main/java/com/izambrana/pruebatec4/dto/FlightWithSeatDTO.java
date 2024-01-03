package com.izambrana.pruebatec4.dto;

import com.izambrana.pruebatec4.model.Flight;
import com.izambrana.pruebatec4.model.FlightSeat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlightWithSeatDTO {
    private Flight flight;
    private List<FlightSeat> seats;
}
