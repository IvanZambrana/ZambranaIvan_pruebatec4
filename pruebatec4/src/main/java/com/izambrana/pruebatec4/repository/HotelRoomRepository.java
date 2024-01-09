package com.izambrana.pruebatec4.repository;

import com.izambrana.pruebatec4.model.HotelRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HotelRoomRepository extends JpaRepository<HotelRoom, Long> {
    HotelRoom findFirstByRoomType(String roomType);

    @Query("SELECT hr FROM HotelRoom hr WHERE hr.hotel.id = :hotelId " +
            "AND hr.roomType = :roomType " +
            "AND hr.available = true " +
            "AND NOT EXISTS (SELECT bh FROM BookHotel bh " +
            "WHERE bh.room = hr AND " +
            "((bh.startDate BETWEEN :startDate AND :endDate) OR (bh.endDate BETWEEN :startDate AND :endDate)))")
    List<HotelRoom> findAvailableRooms(
            @Param("hotelId") Long hotelId,
            @Param("roomType") String roomType,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}