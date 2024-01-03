package com.izambrana.pruebatec4.repository;

import com.izambrana.pruebatec4.model.FlightSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightSeatRepository  extends JpaRepository<FlightSeat, Long> {
}
