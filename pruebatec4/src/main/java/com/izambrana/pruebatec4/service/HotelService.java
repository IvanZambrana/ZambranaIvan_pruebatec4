package com.izambrana.pruebatec4.service;

import com.izambrana.pruebatec4.dto.GuestDTO;
import com.izambrana.pruebatec4.dto.HotelBookingRequestDTO;
import com.izambrana.pruebatec4.dto.HotelWithRoomsDTO;
import com.izambrana.pruebatec4.model.*;
import com.izambrana.pruebatec4.repository.BookHotelRepository;
import com.izambrana.pruebatec4.repository.HotelRepository;
import com.izambrana.pruebatec4.repository.HotelRoomRepository;
import com.izambrana.pruebatec4.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelService implements IHotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    HotelRoomRepository hotelRoomRepository;

    @Autowired
    BookHotelRepository bookHotelRepository;

    @Autowired
    UserRepository userRepository;

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
    public List<Hotel> getHotelsByCity(String city) {
        List<Hotel> hotels = hotelRepository.findByCity(city);

        return hotels.stream()
                .map(hotel -> {
                    boolean hasAvailableRooms = hotel.getRooms().stream()
                            .anyMatch(room -> room.isAvailable());

                    if(hasAvailableRooms)
                        return hotel;
                    else
                        return null;
                })
                .collect(Collectors.toList());
    }


    @Override
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }


    @Override
    public Double bookHotel(HotelBookingRequestDTO request) throws Exception {
        // Buscar el hotel por código
        Hotel hotel = hotelRepository.findByHotelCode(request.getHotelCode());


        if (hotel == null) {
            throw new Exception("Hotel not found");
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


        // Establecer la relación bidireccional con húespedes
        bookHotel.setGuests(new ArrayList<>());

        for (GuestDTO guestDTO : request.getGuests()) {
            User guest = new User();
            guest.setName(guestDTO.getName());
            guest.setLastName(guestDTO.getLastName());
            guest.setDocId(guestDTO.getDocId());
            bookHotel.getGuests().add(guest);
        }

        bookHotelRepository.save(bookHotel);

        // Actualizar la relación bidireccional en la lista de hoteles de cada usuario
        for (User guest : bookHotel.getGuests()) {
            guest.getHotels().add(bookHotel);
            userRepository.save(guest);
        }


        // Calcular el número de días de estancia
        long numberOfDays = ChronoUnit.DAYS.between(request.getStartDate(), request.getEndDate());


        //Se devuelve el precio total
        return calculateTotalPrice(request.getPeopleQ(), bookedRoom.getPrice(), numberOfDays);

    }

    @Override
    public void deleteBookedHotel(Long id) {
        try {
            // Verificar si la reserva existe antes de intentar eliminarla
            Optional<BookHotel> optionalBookHotel = bookHotelRepository.findById(id);

            if (optionalBookHotel.isPresent()) {
                // Eliminar la reserva por su ID
                bookHotelRepository.deleteById(id);
            } else {
                throw new EntityNotFoundException("No reservations found with ID: " + id);
            }
        } catch (Exception e) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());

        }
    }


    //Método para calcular el precio total
    private Double calculateTotalPrice(int peopleQ, Double roomPrice, long numberOfDays) {
        return peopleQ * roomPrice * numberOfDays;
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

    /*@Override
    public List<Hotel> getHotelsByDateAndDestination(LocalDate dateFrom, LocalDate dateTo, String destination) {
        return hotelRepository.findHotelsByDateAndDestination(dateFrom, dateTo, destination);

    }*/

}
