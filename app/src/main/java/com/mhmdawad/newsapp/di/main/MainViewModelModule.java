package com.mhmdawad.newsapp.di.main;

import androidx.lifecycle.ViewModel;

import com.mhmdawad.newsapp.di.ViewModelKey;
import com.mhmdawad.newsapp.ui.main.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);
}
