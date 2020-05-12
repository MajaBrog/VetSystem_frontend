package com.kodilla.frontend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medication {
    private Long id;
    private String medicationName;
    private String dosePerKg;
    private Unit unit;
    private List<Visit_Medication> visit_medicationList;

    public Medication(String medicationName, String dosePerKg, Unit unit) {
        this.medicationName = medicationName;
        this.dosePerKg = dosePerKg;
        this.unit = unit;
    }
}
