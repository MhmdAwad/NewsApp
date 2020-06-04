package com.mhmdawad.newsapp.ui.language;

import androidx.lifecycle.ViewModel;

import com.mhmdawad.newsapp.models.Country;
import com.mhmdawad.newsapp.repository.AppRepository;

import javax.inject.Inject;

public class LanguageViewModel extends ViewModel {


    private AppRepository appRepository;

    @Inject
    LanguageViewModel(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    void changeCountry(Country country){
        appRepository.changeCountry(country);
    }
}
