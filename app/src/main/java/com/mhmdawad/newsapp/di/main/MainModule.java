package com.mhmdawad.newsapp.di.main;


import android.content.SharedPreferences;

import androidx.paging.PagedList;

import com.bumptech.glide.request.RequestOptions;
import com.mhmdawad.newsapp.database.NewsDao;
import com.mhmdawad.newsapp.network.main.MainApi;
import com.mhmdawad.newsapp.paging.NewsDataSource;
import com.mhmdawad.newsapp.paging.NewsDataSourceFactory;
import com.mhmdawad.newsapp.ui.main.MainRepository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class MainModule {

    @Provides
    @MainScope
    static NewsDataSource provideItemDataSource(CompositeDisposable disposable, MainRepository mainRepository) {
        return new NewsDataSource(disposable, mainRepository);
    }

    @Provides
    @MainScope
    static NewsDataSourceFactory provideDataSourceFactory(CompositeDisposable disposable , MainRepository repository) {
        return new NewsDataSourceFactory(repository, disposable);
    }

    @Provides
    @MainScope
    @Named("defaultRequestOption")
    static RequestOptions provideNonCircleRequestOptions() {
        return RequestOptions.centerInsideTransform();
    }

    @Provides
    @MainScope
    static PagedList.Config config(){
        return new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(10)
                .setPageSize(10)
                .build();
    }

    @MainScope
    @Provides
    static MainRepository mainRepositoryInject(MainApi mainApi, CompositeDisposable disposable, NewsDao newsDao, SharedPreferences preferences) {
        return new MainRepository(mainApi, disposable, newsDao, preferences );
    }

}
