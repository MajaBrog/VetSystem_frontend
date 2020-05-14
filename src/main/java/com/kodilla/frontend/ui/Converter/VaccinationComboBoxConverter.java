package com.kodilla.frontend.ui.Converter;

import com.kodilla.frontend.client.VetSystemClient;
import com.kodilla.frontend.domain.Vaccination;
import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

public class VaccinationComboBoxConverter implements Converter<Vaccination, Long> {


    @Override
    public Result<Long> convertToModel(Vaccination vaccination, ValueContext context) {
        if(vaccination==null){
            return Result.ok(null);
        }
        return Result.ok(vaccination.getId());
    }

    @Override
    public Vaccination convertToPresentation(Long id, ValueContext context){
        return null;
    }

    @Override
    public <T> Converter<Vaccination, T> chain(Converter<Long, T> other) {
        return null;
    }
}