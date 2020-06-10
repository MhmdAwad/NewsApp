package com.mhmdawad.newsapp.paging.main.search;


import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.mhmdawad.newsapp.models.ArticlesItem;
import com.mhmdawad.newsapp.network.main.MainApi;

import io.reactivex.disposables.CompositeDisposable;

public class SearchDataSourceFactory extends DataSource.Factory {

    private MainApi mainApi;
    private CompositeDisposable disposable;
    private String search;
    private MutableLiveData<SearchDataSource> mutableLiveData;


    public SearchDataSourceFactory(MainApi mainApi, CompositeDisposable disposable) {
        this.mainApi = mainApi;
        this.disposable = disposable;
        this.search = "madrid";
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource<Integer ,ArticlesItem> create() {
        SearchDataSource dataSource = new SearchDataSource(disposable, mainApi, search);
        mutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<SearchDataSource> getMutableLiveData() {
        return mutableLiveData;
    }

    public void setSearch(String search){
        this.search = search;
    }
}
