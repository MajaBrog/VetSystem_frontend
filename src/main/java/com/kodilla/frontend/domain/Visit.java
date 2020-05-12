package com.kodilla.frontend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visit {
    private Long id;
    private LocalDate dateOfVisit;
    private Long petId;
    private String diagnose;
    private String additionalRecommendation;
    private int weight;
    private List<Visit_Medication> visit_medicationsDtoList;
    private List<Visit_Vaccination> visit_vaccinationsDtoList;

    public Visit(Long petId, String diagnose, String additionalRecommendation, int weight) {
        this.petId = petId;
        this.diagnose = diagnose;
        this.additionalRecommendation = additionalRecommendation;
        this.weight = weight;
    }
}
