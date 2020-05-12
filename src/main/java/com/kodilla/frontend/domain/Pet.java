package com.kodilla.frontend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    private Long id;
    private String chipId;
    private String name;
    private String kind;
    private List<Visit> visitList;
    private LocalDate birthDate;
    private List<ChronicDisease_Pet> chronicDisease_pets;
    private boolean sterilised;
    private LocalDate dateOfSterilization;
    private boolean aggressive;
    private Long clientId;

    public Pet(String chipId, String name, String kind, LocalDate birthDate, boolean sterilised, LocalDate dateOfSterilization, boolean aggressive, Long clientId) {
        this.chipId = chipId;
        this.name = name;
        this.kind = kind;
        this.birthDate = birthDate;
        this.sterilised = sterilised;
        this.dateOfSterilization = dateOfSterilization;
        this.aggressive = aggressive;
        this.clientId = clientId;
    }

}
