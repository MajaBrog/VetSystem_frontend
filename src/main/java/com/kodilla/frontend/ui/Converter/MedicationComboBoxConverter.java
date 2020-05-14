package com.kodilla.frontend.ui.Converter;

import com.kodilla.frontend.client.VetSystemClient;
import com.kodilla.frontend.domain.Medication;
import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

public class MedicationComboBoxConverter implements Converter<Medication, Long> {

    @Override
    public Result<Long> convertToModel(Medication medication, ValueContext context) {
        if(medication==null){
            return Result.ok(null);
        }
        return Result.ok(medication.getId());
    }

    @Override
    public Medication convertToPresentation(Long id, ValueContext context){
        return null;
    }

    @Override
    public <T> Converter<Medication, T> chain(Converter<Long, T> other) {
        return null;
    }
}

