package com.izambrana.pruebatec4;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.izambrana.pruebatec4.model.Hotel;
import com.izambrana.pruebatec4.repository.HotelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private HotelRepository hotelRepository;

    @Test
    public void getHotelsReturnsListOfHotels() throws Exception {
        // Limpiar cualquier dato existente en la base de datos
        hotelRepository.deleteAll();

        // Realizar una solicitud para obtener la lista de hoteles
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/agency/hotels"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Verificar que la respuesta contiene una lista de hoteles vacía
        String responseContent = result.getResponse().getContentAsString();
        Hotel[] hotels = objectMapper.readValue(responseContent, Hotel[].class);

        assert hotels != null;
        assert hotels.length == 0;

        // Crear y guardar un hotel en la base de datos
        Hotel hotel = new Hotel();
        hotel.setHotelCode("H-0001");
        hotel.setName("Hotel Ejemplo");
        hotel.setCity("Ciudad Ejemplo");

        hotelRepository.save(hotel);

        // Realizar la misma solicitud nuevamente
        result = mockMvc.perform(MockMvcRequestBuilders.get("/agency/hotels"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Verificar que la respuesta ahora contiene la lista de hoteles
        responseContent = result.getResponse().getContentAsString();
        hotels = objectMapper.readValue(responseContent, Hotel[].class);

        assert hotels != null;
        assert hotels.length == 1;
        assert hotels[0].getHotelCode().equals("H-0001");
    }
}
