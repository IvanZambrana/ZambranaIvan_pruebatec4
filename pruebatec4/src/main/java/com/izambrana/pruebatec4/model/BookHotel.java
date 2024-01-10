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
public class BookHotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private HotelRoom room;

    private LocalDate startDate;
    private LocalDate endDate;
    private int peopleQ;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> guests;
}

