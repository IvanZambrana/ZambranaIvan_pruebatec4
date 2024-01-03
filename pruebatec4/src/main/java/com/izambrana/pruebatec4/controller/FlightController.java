package com.izambrana.pruebatec4.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.izambrana.pruebatec4.dto.FlightWithSeatDTO;
import com.izambrana.pruebatec4.model.Flight;
import com.izambrana.pruebatec4.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/agency")
public class FlightController {

    @Autowired
    private IFlightService flightService;

    //Listar vuelos
    @GetMapping("/flights")
    public List<Flight> getFlights(){
        return flightService.getFlights();
    }

    //Crear vuelos con asientos
    @PostMapping("/flights/new")
    public String saveFlightWithSeats(@RequestBody FlightWithSeatDTO flightWithSeatDTO){
        flightService.saveFlightWithSeats(flightWithSeatDTO);
        return "Flight and seats saved succesfully";
    }

    //Crear vuelos sin asientos asociados
    @PostMapping("/flights/new/blank")
    public String saveFlight(@RequestBody Flight flight){
        flightService.saveFlight(flight);
        return "Flight saved succesfully";
    }

    //Mostrar info de un vuelo concreto
    @GetMapping("/flights/{id}")
    public Flight getFlightById(@PathVariable Long id){
        return flightService.getFlightById(id);
    }

    //Editar datos de un vuelo
    @PutMapping("/flights/edit/{id}")
    public Flight editFlight(@PathVariable Long id,
                             @RequestParam String flightCode,
                             @RequestParam String origin,
                             @RequestParam String destination,
                             @RequestParam @JsonFormat(pattern = "yyyy-MM-dd") LocalDate departureDate){
        Flight flight = flightService.getFlightById(id);

        flight.setFlightCode(flightCode);
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setDepartureDate(departureDate);

        flightService.saveFlight(flight);
        return flight;
    }

    //Eliminar un vuelo
    @DeleteMapping("/flights/delete/{id}")
    public String deleteFlight(@PathVariable Long id){
        flightService.deleteFlight(id);
        return "Flight deleted succesfully";
    }
}
