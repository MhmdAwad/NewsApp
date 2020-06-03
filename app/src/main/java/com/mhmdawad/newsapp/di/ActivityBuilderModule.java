package com.mhmdawad.newsapp.di;

import com.mhmdawad.newsapp.di.main.MainModule;
import com.mhmdawad.newsapp.di.main.MainViewModelModule;
import com.mhmdawad.newsapp.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = {
            MainViewModelModule.class,
            MainModule.class,

    })
    abstract MainActivity mainActivityInject();
}
