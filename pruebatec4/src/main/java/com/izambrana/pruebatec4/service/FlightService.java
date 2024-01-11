package com.izambrana.pruebatec4.service;

import com.izambrana.pruebatec4.dto.FlightBookingRequestDTO;
import com.izambrana.pruebatec4.dto.FlightWithSeatDTO;
import com.izambrana.pruebatec4.dto.GuestDTO;
import com.izambrana.pruebatec4.model.*;
import com.izambrana.pruebatec4.repository.BookFlightRepository;
import com.izambrana.pruebatec4.repository.FlightRepository;
import com.izambrana.pruebatec4.repository.FlightSeatRepository;
import com.izambrana.pruebatec4.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService implements IFlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    FlightSeatRepository flightSeatRepository;

    @Autowired
    BookFlightRepository bookFlightRepository;

    @Autowired
    UserRepository userRepository;

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
            throw new Exception("Flight not found");
        }


        // Obtener el asiento disponible según el tipo de asiento
        FlightSeat seat = flightSeatRepository.findFirstByFlightAndSeatTypeAndAvailableIsTrue(
                flight, request.getSeatType());

        if (seat == null) {
            throw new Exception("No seats available");
        }

        //Crear la reserva de vuelo
        BookFlight bookFlight = new BookFlight();
        bookFlight.setDate(request.getDate());
        bookFlight.setOrigin(request.getOrigin());
        bookFlight.setDestination(request.getDestination());
        bookFlight.setPeopleQ(request.getPeopleQ());
        bookFlight.setSeatType(request.getSeatType());

        //Establecer la relaciónbidireccional con pasajeros
        bookFlight.setPassengers(new ArrayList<>());

        for (GuestDTO passenger : request.getPassengers()) {
            User p = new User();
            p.setName(passenger.getName());
            p.setLastName(passenger.getLastName());
            p.setDocId(passenger.getDocId());
            bookFlight.getPassengers().add(p);
        }

        flightSeatRepository.save(seat);

        // Actualizar la relación bidireccional en la lista de hoteles de cada usuario
        for (User p : bookFlight.getPassengers()) {
            p.getBookFlights().add(bookFlight);
            userRepository.save(p);
        }


        // Calcular el precio total en función de la cantidad de personas y el precio del asiento
        Double totalPrice = calculateTotalPrice(request.getPeopleQ(), seat.getPrice());

        // Reservar el vuelo y actualizar el estado del asiento
        seat.setAvailable(false);


        // Aquí puedes realizar cualquier otra lógica necesaria para la reserva

        return totalPrice;
    }

    //Método para calcular el precio total
    private Double calculateTotalPrice(int peopleQ, Double seatPrice) {
        return peopleQ * seatPrice;
    }

    public void deleteBookedFlight(Long id) {
        try {
            // Verificar si la reserva existe antes de intentar eliminarla
            Optional<BookFlight> optionalBookFlight = bookFlightRepository.findById(id);

            if (optionalBookFlight.isPresent()) {
                // Eliminar la reserva por su ID
                bookFlightRepository.deleteById(id);
            } else {
                throw new EntityNotFoundException("No flight booking found with ID: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error deleting flight booking: " + e.getMessage());
        }
    }

}
