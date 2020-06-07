package com.mhmdawad.newsapp.ui.main.fragment.home;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.mhmdawad.newsapp.models.ArticlesItem;
import com.mhmdawad.newsapp.paging.api.NewsDataSource;
import com.mhmdawad.newsapp.paging.api.NewsDataSourceFactory;
import com.mhmdawad.newsapp.repository.AppRepository;

import com.mhmdawad.newsapp.utils.DataStatus;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {

    private AppRepository appRepository;
    private LiveData<PagedList<ArticlesItem>> itemPagedList;
    private NewsDataSourceFactory newsDataSourceFactory;
    private PagedList.Config config;
    private LiveData<DataStatus> newsData;

    @Inject
    HomeViewModel(AppRepository appRepository, PagedList.Config config, NewsDataSourceFactory factory) {
        this.appRepository = appRepository;
        this.newsDataSourceFactory = factory;
        this.config = config;
    }

    void fetchData(){
        itemPagedList = new LivePagedListBuilder(newsDataSourceFactory, config).build();
        newsData = Transformations.switchMap(newsDataSourceFactory.getMutableLiveData(), NewsDataSource::getMutableLiveData);
    }
    void refreshData() {
        Log.d("TAG", "refreshData: ");
        if (itemPagedList.getValue() != null) {
            itemPagedList.getValue().getDataSource().invalidate();
        }
    }
    LiveData<String> getCountry() {
        appRepository.getCountryImage();
        return appRepository.getSelectedCountryImage();
    }

    LiveData<DataStatus> getDataStatus() {
        return newsData;
    }

    LiveData<String> changedCountry() {
        return appRepository.getSelectedCountryImage();
    }

    LiveData<PagedList<ArticlesItem>> getItemPagedList() {
        return itemPagedList;
    }
}
