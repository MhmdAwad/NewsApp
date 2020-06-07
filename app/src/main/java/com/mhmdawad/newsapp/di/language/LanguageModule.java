package com.mhmdawad.newsapp.di.language;

import android.app.Application;

import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.mhmdawad.newsapp.ui.language.LanguageAdapter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class LanguageModule {

    @Provides
    @LanguageScope
    static GridLayoutManager provideLinearLayoutManager(Application application) {
        return new GridLayoutManager(application, 2);
    }


    @Provides
    @LanguageScope
    static LanguageAdapter provideMainAdapter(RequestManager requestManager, @Named("circleRequestOption") RequestOptions requestOptions) {
        return new LanguageAdapter(requestManager,requestOptions);
    }
}
