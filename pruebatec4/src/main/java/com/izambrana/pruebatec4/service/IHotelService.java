package com.izambrana.pruebatec4.service;

import com.izambrana.pruebatec4.model.Hotel;

import java.util.List;

public interface IHotelService {

    public void saveHotel (Hotel hotel);
    public List<Hotel> getHotels();

    public Hotel getHotelById(Long id);

    public void deleteHotel(Long id);
}