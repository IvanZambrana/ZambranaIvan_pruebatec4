package com.izambrana.pruebatec4.repository;

import com.izambrana.pruebatec4.model.HotelRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRoomRepository extends JpaRepository<HotelRoom, Long> {

}