package com.izambrana.pruebatec4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class FlightSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;
    private String seatType;
    private double price;
    private boolean available;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
}
