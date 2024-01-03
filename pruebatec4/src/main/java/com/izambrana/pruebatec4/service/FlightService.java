package com.izambrana.pruebatec4.service;

import com.izambrana.pruebatec4.dto.FlightWithSeatDTO;
import com.izambrana.pruebatec4.model.Flight;
import com.izambrana.pruebatec4.model.FlightSeat;
import com.izambrana.pruebatec4.repository.FlightRepository;
import com.izambrana.pruebatec4.repository.FlightSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService implements IFlightService{

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    FlightSeatRepository flightSeatRepository;
    @Override
    public void saveFlight(Flight flight) {
        flightRepository.save(flight);
    }

    @Override
    public void saveFlightWithSeats(FlightWithSeatDTO flightWithSeatDTO) {
        Flight flight = flightWithSeatDTO.getFlight();
        List<FlightSeat> seats =flightWithSeatDTO.getSeats();

        //Guardar el vuelo
        flightRepository.save(flight);

        //Asociar los asientos con el vuelo y guardarlos
        for (FlightSeat seat : seats) {
            seat.setFlight(flight);
            flightSeatRepository.save(seat);
        }
    }

    @Override
    public List<Flight> getFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }
}
