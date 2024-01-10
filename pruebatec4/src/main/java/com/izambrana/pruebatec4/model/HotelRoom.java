package com.izambrana.pruebatec4.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HotelRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int roomNumber;
    private String roomType;
    private double price;
    private boolean available;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;


    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<BookHotel> reservations;
}
