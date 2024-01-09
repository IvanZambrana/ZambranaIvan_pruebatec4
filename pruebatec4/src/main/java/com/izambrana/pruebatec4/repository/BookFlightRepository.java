package com.izambrana.pruebatec4.repository;

import com.izambrana.pruebatec4.model.BookFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookFlightRepository   extends JpaRepository<BookFlight, Long> {
}
