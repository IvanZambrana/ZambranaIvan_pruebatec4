package com.izambrana.pruebatec4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GuestDTO {
    private String name;
    private String lastName;
    private String docId;
}
