package com.mhmdawad.newsapp.di;

import androidx.lifecycle.ViewModel;

import com.mhmdawad.newsapp.di.ViewModelKey;
import com.mhmdawad.newsapp.ui.language.LanguageViewModel;
import com.mhmdawad.newsapp.ui.main.MainViewModel;
import com.mhmdawad.newsapp.ui.main.fragment.home.HomeViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ActivityViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LanguageViewModel.class)
    abstract ViewModel bindLanguageViewModel(LanguageViewModel languageViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    abstract ViewModel bindHomeViewModel(HomeViewModel homeViewModel);
}
