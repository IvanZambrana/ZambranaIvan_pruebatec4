package com.izambrana.pruebatec4.service;

import com.izambrana.pruebatec4.dto.HotelWithRoomsDTO;
import com.izambrana.pruebatec4.model.Flight;
import com.izambrana.pruebatec4.model.Hotel;
import com.izambrana.pruebatec4.dto.HotelBookingRequestDTO;

import java.time.LocalDate;
import java.util.List;

public interface IHotelService {

    public void saveHotelWithRooms(HotelWithRoomsDTO hotelWithRoomsDTO);
    public List<Hotel> getHotels();

    public Hotel getHotelById(Long id);

    public List<Hotel> getHotelsByCity(String city);

    public void deleteHotel(Long id);
    public Double bookHotel(HotelBookingRequestDTO request) throws Exception;

    void deleteBookedHotel(Long id);

   // List<Hotel> getHotelsByDateAndDestination(
            //LocalDate dateFrom, LocalDate dateTo, String destination);

    void saveHotel(Hotel hotel);
}
