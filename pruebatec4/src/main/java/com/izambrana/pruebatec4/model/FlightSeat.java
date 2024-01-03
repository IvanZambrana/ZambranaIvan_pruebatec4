package com.izambrana.pruebatec4.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private Long id;
    private int number;
    private String seatType;
    private double price;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
}
