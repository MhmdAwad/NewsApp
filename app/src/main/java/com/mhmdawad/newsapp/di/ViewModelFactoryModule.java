package com.mhmdawad.newsapp.di;

import androidx.lifecycle.ViewModelProvider;

import com.mhmdawad.newsapp.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory factory);

}