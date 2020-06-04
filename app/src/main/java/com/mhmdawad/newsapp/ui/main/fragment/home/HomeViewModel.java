package com.mhmdawad.newsapp.ui.main.fragment.home;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mhmdawad.newsapp.models.ArticlesItem;
import com.mhmdawad.newsapp.models.Country;
import com.mhmdawad.newsapp.repository.AppRepository;
import com.mhmdawad.newsapp.ui.main.MainResource;


import java.util.List;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {

    private AppRepository appRepository;

    @Inject
    HomeViewModel(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    LiveData<MainResource<List<ArticlesItem>>> getResponseData() {
        return appRepository.getResponseMediatorLiveData();
    }


}
