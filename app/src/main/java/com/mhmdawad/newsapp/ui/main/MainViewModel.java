package com.mhmdawad.newsapp.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mhmdawad.newsapp.repository.AppRepository;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {


    private AppRepository appRepository;

    @Inject
    MainViewModel(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    LiveData<String> getCountry(){
        return appRepository.getSelectedCountryImage();
    }

}
