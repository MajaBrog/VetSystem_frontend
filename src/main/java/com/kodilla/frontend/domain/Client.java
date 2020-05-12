package com.kodilla.frontend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private Long id;
    private String legalID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Address address;
    private String email;
    private List<Pet> petList;

    public Client(String legalID, String firstName, String lastName, String phoneNumber, Address address, String email) {
        this.legalID = legalID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }

    public Client(Address address) {
        this.address = address;
    }
}
