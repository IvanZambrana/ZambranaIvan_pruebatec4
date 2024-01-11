package com.izambrana.pruebatec4.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightCode;
    private String origin;
    private String destination;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate departureDate;

    @JsonIgnore
    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private List<FlightSeat> seats;
}
