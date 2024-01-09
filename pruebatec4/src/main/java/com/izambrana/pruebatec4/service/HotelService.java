package com.izambrana.pruebatec4.service;

import com.izambrana.pruebatec4.dto.HotelBookingRequestDTO;
import com.izambrana.pruebatec4.dto.HotelWithRoomsDTO;
import com.izambrana.pruebatec4.model.*;
import com.izambrana.pruebatec4.repository.BookHotelRepository;
import com.izambrana.pruebatec4.repository.HotelRepository;
import com.izambrana.pruebatec4.repository.HotelRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService implements IHotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    HotelRoomRepository hotelRoomRepository;

    @Autowired
    BookHotelRepository bookHotelRepository;

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
            room.setAvailable(true); //Todas las habitaciones que se agregan empiezan como disponibles para su reserva
            room.setHotel(hotel);
            //room.setReservations(null);
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


    @Override
    public Double bookHotel(HotelBookingRequestDTO request) throws Exception {
        // Buscar el hotel por código o alguna otra identificación
        Hotel hotel = hotelRepository.findByHotelCode(request.getHotelCode());


        if (hotel == null) {
            throw new Exception("No hotels found");
        }

        // Obtener la lista de habitaciones disponibles según el tipo y fechas especificadas
        List<HotelRoom> availableRooms = hotelRoomRepository.findAvailableRooms(
                hotel.getId(),
                request.getRoomType(),
                request.getStartDate(),
                request.getEndDate()
        );

        // Tomar la primera habitación disponible
        HotelRoom bookedRoom = availableRooms.get(0);


        // Actualizar la disponibilidad de la habitación
        bookedRoom.setAvailable(false);
        hotelRoomRepository.save(bookedRoom);

        // Crear la reserva de hotel
        BookHotel bookHotel = new BookHotel();
        bookHotel.setStartDate(request.getStartDate());
        bookHotel.setEndDate(request.getEndDate());
        bookHotel.setPeopleQ(request.getPeopleQ());
        bookHotel.setRoom(bookedRoom);
        bookHotelRepository.save(bookHotel);

        //Se devuelve el precio total
        return calculateTotalPrice(request.getPeopleQ(), bookedRoom.getPrice());

    }

    //Método para calcular el precio total
    private Double calculateTotalPrice(int peopleQ, Double roomPrice) {
        return peopleQ * roomPrice;
    }

    // Método para obtener el precio de la habitación según el tipo de habitación y reservarla
    private Double getRoomPrice(String roomType) {
        HotelRoom room = hotelRoomRepository.findFirstByRoomType(roomType);
        room.setAvailable(false);
        if (room != null) {
            return room.getPrice();
        } else {
            return 0.0;
        }
    }

}
