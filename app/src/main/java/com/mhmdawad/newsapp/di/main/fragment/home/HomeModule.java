package com.mhmdawad.newsapp.di.main.fragment.home;

import android.app.Application;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.mhmdawad.newsapp.ui.main.MainRepository;
import com.mhmdawad.newsapp.ui.main.fragment.home.HomeAdapter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {


    @HomeScope
    @Provides
    static LinearLayoutManager provideLinearLayoutManager(Application application) {
        return new LinearLayoutManager(application, LinearLayoutManager.VERTICAL, false);
    }

    @HomeScope
    @Provides
    static HomeAdapter provideMainAdapter(RequestManager requestManager, @Named("defaultRequestOption") RequestOptions requestOptions,
                                          MainRepository mainRepository) {
        return new HomeAdapter(requestManager,requestOptions, mainRepository);
    }

}
