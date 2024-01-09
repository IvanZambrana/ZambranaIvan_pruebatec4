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
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hotelCode;
    private String name;
    private String city;

    @JsonManagedReference
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<HotelRoom> rooms;

}
