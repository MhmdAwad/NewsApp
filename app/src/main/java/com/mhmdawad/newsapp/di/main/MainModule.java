package com.mhmdawad.newsapp.di.main;

import android.app.Application;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.mhmdawad.newsapp.database.NewsDao;
import com.mhmdawad.newsapp.database.NewsDatabase;
import com.mhmdawad.newsapp.network.main.MainApi;
import com.mhmdawad.newsapp.ui.main.MainAdapter;
import com.mhmdawad.newsapp.ui.main.MainRepository;


import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @Provides
    static MainRepository mainRepositoryInject(MainApi mainApi, CompositeDisposable disposable, NewsDao newsDao){
        return new MainRepository(mainApi,disposable, newsDao);
    }

    @Provides
    static MainApi provideMainApi(Retrofit retrofit){
        return retrofit.create(MainApi.class);
    }

    @Provides
    static CompositeDisposable provideDisposable(){
        return new CompositeDisposable();
    }

    @Provides
    static NewsDao provideNewsDao(NewsDatabase database){
        return database.newsDao();
    }

    @Provides
    static MainAdapter provideMainAdapter(){
        return new MainAdapter();
    }

    @Provides
    static LinearLayoutManager provideLinearLayoutManager(Application application){
        return new LinearLayoutManager(application, LinearLayoutManager.VERTICAL, false);
    }

}
