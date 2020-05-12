package com.kodilla.frontend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChronicDisease_Pet {
    private Long id;
    private Long petId;
    private Long chronicDiseaseId;
    private LocalDate dateOfDiagnosis;

    public ChronicDisease_Pet(Long petId, Long chronicDiseaseId, LocalDate dateOfDiagnosis) {
        this.petId = petId;
        this.chronicDiseaseId = chronicDiseaseId;
        this.dateOfDiagnosis = dateOfDiagnosis;
    }
    public ChronicDisease_Pet(Long petId) {
        this.petId = petId;
    }
}

