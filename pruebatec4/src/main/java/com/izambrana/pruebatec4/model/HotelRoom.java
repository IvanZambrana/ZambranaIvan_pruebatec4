package com.izambrana.pruebatec4.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HotelRoom {
    @Id
    private Long id;
    private int roomNumber;
    private String roomType;
    private double price;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
