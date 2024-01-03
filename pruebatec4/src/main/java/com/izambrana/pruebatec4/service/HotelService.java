package com.izambrana.pruebatec4.service;

import com.izambrana.pruebatec4.dto.HotelWithRoomsDTO;
import com.izambrana.pruebatec4.model.Hotel;
import com.izambrana.pruebatec4.model.HotelRoom;
import com.izambrana.pruebatec4.repository.HotelRepository;
import com.izambrana.pruebatec4.repository.HotelRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService implements IHotelService{

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    HotelRoomRepository hotelRoomRepository;
    @Override
    public void saveHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    @Override
    public void saveHotelWithRooms(HotelWithRoomsDTO hotelWithRoomsDTO) {
        Hotel hotel = hotelWithRoomsDTO.getHotel();
        List<HotelRoom> rooms = hotelWithRoomsDTO.getRooms();

        // Guardar el hotel
        hotelRepository.save(hotel);

        // Asociar las habitaciones con el hotel y guardarlas
        for (HotelRoom room : rooms) {
            room.setHotel(hotel);
            hotelRoomRepository.save(room);
        }
    }

    @Override
    public List<Hotel> getHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }
}
