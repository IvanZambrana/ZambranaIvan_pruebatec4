package com.izambrana.pruebatec4.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookFlight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    private LocalDate date;
    private String origin;
    private String destination;
    private int peopleQ;
    private String seatType;

    @ManyToMany
    private List<User> passengers;
}
