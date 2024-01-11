package com.izambrana.pruebatec4.controller;

import com.izambrana.pruebatec4.dto.HotelWithRoomsDTO;
import com.izambrana.pruebatec4.model.Hotel;
import com.izambrana.pruebatec4.dto.HotelBookingRequestDTO;
import com.izambrana.pruebatec4.service.IHotelService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/agency")
public class HotelController {

    @Autowired
    private IHotelService hotelService;

    //Listar hoteles
    @GetMapping("/hotels")
    public List<Hotel> getHotels() {
        return hotelService.getHotels();
    }

    //Crear hoteles con habitaciones
    @PostMapping("/hotels/new")
    public String saveHotelWithRooms(@RequestBody HotelWithRoomsDTO hotelWithRoomsDTO) {
        hotelService.saveHotelWithRooms(hotelWithRoomsDTO);
        return "Hotel and rooms saved successfully";
    }

    //Mostrar hotel por ID
    @GetMapping("/hotels/{id}")
    public Hotel getHotelById(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }

    //Editar datos de un hotel
    @PutMapping("/hotels/edit/{id}")
    public Hotel editHotel(@PathVariable Long id,
                           @RequestParam String hotelCode,
                           @RequestParam String hotelName,
                           @RequestParam String city) {

        Hotel hotel = hotelService.getHotelById(id);

        hotel.setHotelCode(hotelCode);
        hotel.setName(hotelName);
        hotel.setCity(city);

        hotelService.saveHotel(hotel);

        return hotel;
    }

    @DeleteMapping("/hotels/delete/{id}")
    public String deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return "Hotel deleted succesfully";
    }

    // Reservar un hotel
    @PostMapping("hotel-booking/new")
    public ResponseEntity<String> bookHotel(@RequestBody HotelBookingRequestDTO request) throws Exception {
        Double totalPrice = hotelService.bookHotel(request);

        return ResponseEntity.ok("Total price: " + totalPrice + " $");

    }

    // Eliminar una reserva de hotel por ID
    @DeleteMapping("hotel-booking/delete/{id}")
    public ResponseEntity<String> deleteBookedHotel(@PathVariable Long id) {
        try {
            hotelService.deleteBookedHotel(id);
            return ResponseEntity.ok("Reserva de hotel eliminada con éxito");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hotels reservations found with ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting hotel reservation: " + e.getMessage());
        }
    }


    //Buscar hotel por destino si quedan habitaciones disponibles
    @GetMapping("/hotel/search")
    public ResponseEntity<List<Hotel>> getHotelsByDestination(
            @RequestParam("destination") String destination) {
        try {
            List<Hotel> hotels = hotelService.getHotelsByCity(destination);
            return ResponseEntity.ok(hotels);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
    }

}
