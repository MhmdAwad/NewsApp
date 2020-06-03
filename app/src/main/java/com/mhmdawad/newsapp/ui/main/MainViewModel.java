package com.mhmdawad.newsapp.ui.main;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.mhmdawad.newsapp.models.ArticlesItem;


import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {

    private MainRepository mainRepository;

    @Inject
    MainViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    LiveData<MainResource<List<ArticlesItem>>> getResponseData(){
        return mainRepository.getResponseMediatorLiveData();
    }


}
