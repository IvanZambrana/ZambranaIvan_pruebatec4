package com.izambrana.pruebatec4.repository;

import com.izambrana.pruebatec4.model.Flight;
import com.izambrana.pruebatec4.model.FlightSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightSeatRepository  extends JpaRepository<FlightSeat, Long> {
    FlightSeat findFirstBySeatType(String seatType);

    FlightSeat findFirstByFlightAndSeatTypeAndAvailableIsTrue(Flight flight, String seatType);
}
