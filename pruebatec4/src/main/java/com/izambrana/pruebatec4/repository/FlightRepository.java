package com.izambrana.pruebatec4.repository;

import com.izambrana.pruebatec4.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    //Query encargada de filtrar vuelos por fechas, origen y destino
    @Query("SELECT f FROM Flight f " +
            "WHERE f.departureDate BETWEEN :dateFrom AND :dateTo " +
            "AND f.origin = :origin " +
            "AND f.destination = :destination")
    List<Flight> findFlightsByDateAndDestination(
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo,
            @Param("origin") String origin,
            @Param("destination") String destination);

    Flight findByFlightCode(String flightCode);

    Flight findFlightByOriginAndDestinationAndDepartureDate(String origin, String destination, LocalDate date);
}

