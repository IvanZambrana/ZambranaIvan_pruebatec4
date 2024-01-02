package com.izambrana.pruebatec4.service;

import com.izambrana.pruebatec4.model.Flight;

import java.util.List;

public interface IFlightService {
    public void saveFlight (Flight flight);
    public List<Flight> getFlights();

    public Flight getFlightById(Long id);

    public void deleteFlight(Long id);

}
