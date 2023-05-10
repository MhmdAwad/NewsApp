package com.mhmdawad.newsapp.ui.main.fragment.home;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.mhmdawad.newsapp.models.ArticlesItem;
import com.mhmdawad.newsapp.paging.main.category.CategoryDataSourceFactory;
import com.mhmdawad.newsapp.paging.main.home.HomeDataSource;
import com.mhmdawad.newsapp.paging.main.home.HomeDataSourceFactory;
import com.mhmdawad.newsapp.ui.main.MainRepository;

import com.mhmdawad.newsapp.utils.DataStatus;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {

    private MainRepository mainRepository;
    private LiveData<PagedList<ArticlesItem>> itemPagedList;
    private HomeDataSourceFactory homeFactory;
    private PagedList.Config config;
    private LiveData<DataStatus> newsData;
    private MutableLiveData<ArticlesItem> articleDetails;


    @Inject
    HomeViewModel(MainRepository mainRepository, PagedList.Config config, HomeDataSourceFactory homeFactory) {
        this.mainRepository = mainRepository;
        this.homeFactory = homeFactory;
        this.config = config;
        this.articleDetails = new MutableLiveData<>();
    }

    void fetchTopNewsData() {
        itemPagedList = new LivePagedListBuilder(homeFactory, config).build();
        newsData = Transformations.switchMap(homeFactory.getMutableLiveData(), HomeDataSource::getMutableLiveData);
    }

    void refreshData() {
        if (itemPagedList.getValue() != null) {
            itemPagedList.getValue().getDataSource().invalidate();
        }
    }

    public void openArticleDetails(ArticlesItem articlesItem) {
        articleDetails.setValue(articlesItem);
    }

    LiveData<ArticlesItem> observeArticleDetails() {
        return articleDetails;
    }

    void resetArticleDetails() {
        articleDetails.setValue(null);
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
