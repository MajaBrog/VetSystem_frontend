package com.kodilla.frontend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visit_Vaccination {
    private Long id;
    private Long visitId;
    private Long vaccinationId;
    private String dose;
    private Unit unit;
    private LocalDate remindDate;

    public Visit_Vaccination(Long visitId, Long vaccinationId, String dose, Unit unit, LocalDate remindDate) {
        this.visitId = visitId;
        this.vaccinationId = vaccinationId;
        this.dose = dose;
        this.unit = unit;
        this.remindDate = remindDate;
    }
}
