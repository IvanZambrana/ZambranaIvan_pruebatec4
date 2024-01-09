package com.izambrana.pruebatec4.repository;

import com.izambrana.pruebatec4.model.BookHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookHotelRepository extends JpaRepository<BookHotel, Long> {
}
