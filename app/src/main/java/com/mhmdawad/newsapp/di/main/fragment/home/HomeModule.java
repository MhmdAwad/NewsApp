package com.mhmdawad.newsapp.di.main.fragment.home;

import android.app.Application;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.mhmdawad.newsapp.ui.main.fragment.home.HomeAdapter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {

    @Provides
    static LinearLayoutManager provideLinearLayoutManager(Application application) {
        return new LinearLayoutManager(application, LinearLayoutManager.VERTICAL, false);
    }

    @Provides
    static HomeAdapter provideMainAdapter(RequestManager requestManager, @Named("defaultRequestOption") RequestOptions requestOptions) {
        return new HomeAdapter(requestManager,requestOptions);
    }

}
