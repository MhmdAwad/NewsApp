package com.mhmdawad.newsapp.di;

import androidx.lifecycle.ViewModelProvider;

import com.mhmdawad.newsapp.viewModels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory factory);

}