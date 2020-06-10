package com.mhmdawad.newsapp.paging.main.category;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.mhmdawad.newsapp.models.ArticlesItem;
import com.mhmdawad.newsapp.network.main.MainApi;
import com.mhmdawad.newsapp.ui.main.MainRepository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.disposables.CompositeDisposable;

public class CategoryDataSourceFactory extends DataSource.Factory {

    private MainApi mainApi;
    private CompositeDisposable disposable;
    private String category;
    private MutableLiveData<CategoryDataSource> mutableLiveData;


    public CategoryDataSourceFactory(MainApi mainApi, CompositeDisposable disposable) {
        this.mainApi = mainApi;
        this.disposable = disposable;
        this.category = "world";
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource<Integer ,ArticlesItem> create() {
        Log.d("MMMMMM", "create: " );
        CategoryDataSource dataSource = new CategoryDataSource(disposable, mainApi,category);
        mutableLiveData.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<CategoryDataSource> getMutableLiveData() {
        return mutableLiveData;
    }

    public void setCategory(String category){
        this.category = category;
    }
}
