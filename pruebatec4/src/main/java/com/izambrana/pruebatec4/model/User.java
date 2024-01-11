package com.izambrana.pruebatec4.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private String docId;

    @ManyToMany(mappedBy = "passengers", cascade = CascadeType.ALL)
    private List<BookFlight> bookFlights = new ArrayList<>();


    @ManyToMany(mappedBy = "guests", cascade = CascadeType.ALL)
    private List<BookHotel> hotels = new ArrayList<>();
}
