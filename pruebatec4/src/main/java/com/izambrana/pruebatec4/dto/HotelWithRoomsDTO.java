package com.izambrana.pruebatec4.dto;

import com.izambrana.pruebatec4.model.Hotel;
import com.izambrana.pruebatec4.model.HotelRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelWithRoomsDTO {
    private Hotel hotel;
    private List<HotelRoom> rooms;
}
