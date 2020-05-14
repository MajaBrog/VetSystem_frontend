package com.kodilla.frontend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visit_Medication {
    private Long id;
    private Long visitId;
    private Long medicationId;
    private String dose;
    private Unit unit;

    public Visit_Medication(Long visitId, Long medicationId, String dose, Unit unit) {
        this.visitId = visitId;
        this.medicationId = medicationId;
        this.dose = dose;
        this.unit = unit;
    }

    public Visit_Medication(Long visitId) {
        this.visitId = visitId;
    }
}
