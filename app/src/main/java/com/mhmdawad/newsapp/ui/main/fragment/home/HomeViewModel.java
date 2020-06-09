package com.mhmdawad.newsapp.ui.main.fragment.home;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.mhmdawad.newsapp.models.ArticlesItem;
import com.mhmdawad.newsapp.paging.NewsDataSource;
import com.mhmdawad.newsapp.paging.NewsDataSourceFactory;
import com.mhmdawad.newsapp.ui.main.MainRepository;

import com.mhmdawad.newsapp.utils.DataStatus;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {

    private MainRepository mainRepository;
    private LiveData<PagedList<ArticlesItem>> itemPagedList;
    private NewsDataSourceFactory newsDataSourceFactory;
    private PagedList.Config config;
    private LiveData<DataStatus> newsData;


    @Inject
    HomeViewModel(MainRepository mainRepository, PagedList.Config config, NewsDataSourceFactory factory) {
        this.mainRepository = mainRepository;
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

    LiveData<ArticlesItem> observeArticleDetails(){
        return mainRepository.getArticleDetails();
    }

    LiveData<String> getCountry() {
        mainRepository.getCountryImage();
        return mainRepository.getSelectedCountryImage();
    }

    LiveData<DataStatus> getDataStatus() {
        return newsData;
    }

    LiveData<PagedList<ArticlesItem>> getItemPagedList() {
        return itemPagedList;
    }
}
