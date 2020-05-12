package com.kodilla.frontend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String street;
    private int houseNumber;
    private int homeNumber;
    private String city;
    private String postcode;
}
