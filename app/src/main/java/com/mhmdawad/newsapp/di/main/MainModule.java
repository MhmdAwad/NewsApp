package com.mhmdawad.newsapp.di.main;


import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;

import androidx.paging.PagedList;

import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.mhmdawad.newsapp.paging.api.NewsDataSource;
import com.mhmdawad.newsapp.paging.api.NewsDataSourceFactory;
import com.mhmdawad.newsapp.repository.AppRepository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class MainModule {

    @Provides
    @MainScope
    static NewsDataSource provideItemDataSource(CompositeDisposable disposable, AppRepository appRepository) {
        return new NewsDataSource(disposable, appRepository);
    }

    @Provides
    @MainScope
    static NewsDataSourceFactory provideDataSourceFactory(CompositeDisposable disposable , AppRepository repository) {
        return new NewsDataSourceFactory(repository, disposable);
    }

    @Provides
    @MainScope
    @Named("defaultRequestOption")
    static RequestOptions provideNonCircleRequestOptions() {
        return RequestOptions.centerInsideTransform().override(300, 300);
    }

    @Provides
    @MainScope
    static PagedList.Config config(){
        return (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(20).build();
    }



}
