package com.izambrana.pruebatec4.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;
    private String surname;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "book_flight_id")
    private BookFlight bookFlight;


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "book_hotel_id")
    private BookHotel bookHotel;
}
