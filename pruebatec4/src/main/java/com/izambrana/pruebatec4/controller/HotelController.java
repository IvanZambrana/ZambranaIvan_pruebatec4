package com.izambrana.pruebatec4.controller;

import com.izambrana.pruebatec4.dto.HotelWithRoomsDTO;
import com.izambrana.pruebatec4.model.Hotel;
import com.izambrana.pruebatec4.dto.HotelBookingRequestDTO;
import com.izambrana.pruebatec4.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agency")
public class HotelController {

    @Autowired
    private IHotelService hotelService;

    //Listar hoteles
    @GetMapping("/hotels")
    public List<Hotel> getHotels(){
        return  hotelService.getHotels();
    }

    //Crear hoteles con habitaciones
    @PostMapping("/hotels/new")
    public String saveHotelWithRooms(@RequestBody HotelWithRoomsDTO hotelWithRoomsDTO) {
        hotelService.saveHotelWithRooms(hotelWithRoomsDTO);
        return "Hotel and rooms saved successfully";
    }

    //Crear hoteles sin habitaciones asociadas
    @PostMapping("/hotels/new/blank")
    public String saveHotel(@RequestBody Hotel hotel){
        hotelService.saveHotel(hotel);
        return "Hotel saved succesfully";
    }

    @GetMapping("/hotels/{id}")
    public Hotel getHotelById(@PathVariable Long id){
        return hotelService.getHotelById(id);
    }

    @PutMapping("/hotels/edit/{id}")
    public Hotel editHotel(@PathVariable Long id,
                           @RequestParam String hotelCode,
                           @RequestParam String hotelName,
                           @RequestParam String city){

        Hotel hotel = hotelService.getHotelById(id);

        hotel.setHotelCode(hotelCode);
        hotel.setName(hotelName);
        hotel.setCity(city);

        hotelService.saveHotel(hotel);

        return hotel;
    }

    @DeleteMapping("/hotels/delete/{id}")
    public String deleteHotel(@PathVariable Long id){
        hotelService.deleteHotel(id);
        return "Hotel deleted succesfully";
    }

    // Reservar habitaciones de hotel
    /*@PostMapping("/hotel-booking/new")
    public String bookHotel(@RequestBody HotelBookingRequestDTO hotel) throws Exception {
        Double totalPrice = hotelService.bookHotel(hotel);
        return "Total price: " + totalPrice + " $";
    }*/
}
