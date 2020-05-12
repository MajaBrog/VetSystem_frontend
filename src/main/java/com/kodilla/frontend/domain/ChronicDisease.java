package com.kodilla.frontend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChronicDisease{
    private Long id;
    private String name;
    private List<ChronicDisease_Pet> chronicDisease_PetList;

    public ChronicDisease(String name) {
        this.name = name;
    }
}
