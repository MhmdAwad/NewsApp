package com.mhmdawad.newsapp.paging;


import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.mhmdawad.newsapp.models.ArticlesItem;
import com.mhmdawad.newsapp.ui.main.MainRepository;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class NewsDataSourceFactory extends DataSource.Factory {

    private MainRepository mainRepository;
    private CompositeDisposable disposable;
    private MutableLiveData<NewsDataSource> mutableLiveData;

    @Inject
    public NewsDataSourceFactory(MainRepository mainRepository, CompositeDisposable disposable) {
        this.mainRepository = mainRepository;
        this.disposable = disposable;
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource<Integer ,ArticlesItem> create() {
        NewsDataSource dataSource = new NewsDataSource(disposable, mainRepository);
        mutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<NewsDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
