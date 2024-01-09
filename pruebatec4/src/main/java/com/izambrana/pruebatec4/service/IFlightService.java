package com.izambrana.pruebatec4.service;

import com.izambrana.pruebatec4.dto.FlightWithSeatDTO;
import com.izambrana.pruebatec4.model.Flight;
import com.izambrana.pruebatec4.dto.FlightBookingRequestDTO;

import java.time.LocalDate;
import java.util.List;

public interface IFlightService {
    public void saveFlight (Flight flight);
    public void saveFlightWithSeats(FlightWithSeatDTO flightWithSeatDTO);
    public List<Flight> getFlights();
    public Flight getFlightById(Long id);
    public void deleteFlight(Long id);
    List<Flight> getFlightsByDateAndDestination(
            LocalDate dateFrom, LocalDate dateTo, String origin, String destination);

    //Double bookFlight(BookFlight bookFlight);
    Double bookFlight(FlightBookingRequestDTO flightBookingRequest) throws Exception;
}

