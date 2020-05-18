package com.kodilla.frontend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vaccination {
    private Long id;
    private String name;
    private String disease;
    private String dosePerKg;
    private Unit unit;
    private boolean mandatory;
    private List<Visit_Vaccination> visit_vaccinationsDtoList;

    public Vaccination(String name, String disease, String dosePerKg, Unit unit, boolean mandatory) {
        this.name = name;
        this.disease = disease;
        this.dosePerKg = dosePerKg;
        this.unit = unit;
        this.mandatory = mandatory;
    }
}
