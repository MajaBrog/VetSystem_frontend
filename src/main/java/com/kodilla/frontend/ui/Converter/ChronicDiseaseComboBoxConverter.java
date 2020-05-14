package com.kodilla.frontend.ui.Converter;

import com.kodilla.frontend.client.VetSystemClient;
import com.kodilla.frontend.domain.ChronicDisease;
import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

public class ChronicDiseaseComboBoxConverter implements Converter<ChronicDisease, Long> {


    @Override
    public Result<Long> convertToModel(ChronicDisease chronicDisease, ValueContext context) {
        if(chronicDisease==null){
            return Result.ok(null);
        }
        return Result.ok(chronicDisease.getId());
    }

    @Override
    public ChronicDisease convertToPresentation(Long id, ValueContext context){
        return null;
    }

    @Override
    public <T> Converter<ChronicDisease, T> chain(Converter<Long, T> other) {
        return null;
    }
}
