package com.izambrana.pruebatec4.service;

import com.izambrana.pruebatec4.dto.FlightBookingRequestDTO;
import com.izambrana.pruebatec4.dto.FlightWithSeatDTO;
import com.izambrana.pruebatec4.model.*;
import com.izambrana.pruebatec4.repository.BookFlightRepository;
import com.izambrana.pruebatec4.repository.FlightRepository;
import com.izambrana.pruebatec4.repository.FlightSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FlightService implements IFlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    FlightSeatRepository flightSeatRepository;

    @Autowired
    BookFlightRepository bookFlightRepository;

    @Override
    public void saveFlight(Flight flight) {
        flightRepository.save(flight);
    }

    @Override
    public void saveFlightWithSeats(FlightWithSeatDTO flightWithSeatDTO) {
        Flight flight = flightWithSeatDTO.getFlight();
        List<FlightSeat> seats = flightWithSeatDTO.getSeats();

        //Guardar el vuelo
        flightRepository.save(flight);

        //Asociar los asientos con el vuelo y guardarlos
        for (FlightSeat seat : seats) {
            seat.setAvailable(true);
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

    @Override
    public List<Flight> getFlightsByDateAndDestination(LocalDate dateFrom, LocalDate dateTo, String origin, String destination) {
        return flightRepository.findFlightsByDateAndDestination(dateFrom, dateTo, origin, destination);
    }


    public Double bookFlight(FlightBookingRequestDTO request) throws Exception {
        // Buscar el vuelo por origen, destino y fecha
        Flight flight = flightRepository.findFlightByOriginAndDestinationAndDepartureDate(
                request.getOrigin(), request.getDestination(), request.getDate());

        if (flight == null) {
            throw new Exception("No se encontró un vuelo para la ruta y fecha especificadas.");
        }

        // Obtener el asiento disponible según el tipo de asiento
        FlightSeat seat = flightSeatRepository.findFirstByFlightAndSeatTypeAndAvailableIsTrue(
                flight, request.getSeatType());

        if (seat == null) {
            throw new Exception("No hay asientos disponibles para el tipo especificado.");
        }

        // Calcular el precio total en función de la cantidad de personas y el precio del asiento
        Double totalPrice = calculateTotalPrice(request.getPeopleQ(), seat.getPrice());

        // Reservar el vuelo y actualizar el estado del asiento
        seat.setAvailable(false);
        flightSeatRepository.save(seat);

        // Aquí puedes realizar cualquier otra lógica necesaria para la reserva

        return totalPrice;
    }

    //Método para calcular el precio total
    private Double calculateTotalPrice(int peopleQ, Double seatPrice) {
        return peopleQ * seatPrice;
    }


}
