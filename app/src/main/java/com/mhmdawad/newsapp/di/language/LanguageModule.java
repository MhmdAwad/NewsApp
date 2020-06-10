package com.mhmdawad.newsapp.di.language;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.mhmdawad.newsapp.di.ViewModelKey;
import com.mhmdawad.newsapp.ui.language.LanguageViewModel;
import com.mhmdawad.newsapp.ui.main.MainRepository;
import com.mhmdawad.newsapp.ui.language.LanguageAdapter;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public abstract class LanguageModule {

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

    @Binds
    @IntoMap
    @ViewModelKey(LanguageViewModel.class)
    abstract ViewModel bindLanguageViewModel(LanguageViewModel homeViewModel);
}
