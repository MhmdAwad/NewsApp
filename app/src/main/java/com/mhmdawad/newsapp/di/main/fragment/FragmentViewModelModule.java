package com.mhmdawad.newsapp.di.main.fragment;

import androidx.lifecycle.ViewModel;

import com.mhmdawad.newsapp.di.ViewModelKey;
import com.mhmdawad.newsapp.ui.main.fragment.home.HomeViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class FragmentViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    abstract ViewModel bindHomeViewModel(HomeViewModel homeViewModel);
}
