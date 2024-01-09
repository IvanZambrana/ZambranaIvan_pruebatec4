package com.izambrana.pruebatec4.service;

import com.izambrana.pruebatec4.dto.HotelWithRoomsDTO;
import com.izambrana.pruebatec4.model.Hotel;
import com.izambrana.pruebatec4.dto.HotelBookingRequestDTO;

import java.util.List;

public interface IHotelService {

    public void saveHotel (Hotel hotel);
    public void saveHotelWithRooms(HotelWithRoomsDTO hotelWithRoomsDTO);
    public List<Hotel> getHotels();

    public Hotel getHotelById(Long id);

    public void deleteHotel(Long id);
    public Double bookHotel(HotelBookingRequestDTO request) throws Exception;
}
