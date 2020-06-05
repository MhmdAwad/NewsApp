package com.mhmdawad.newsapp;


import androidx.databinding.DataBindingUtil;

import com.mhmdawad.newsapp.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class BaseApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
//        DDDComp bindingComponent =  DDD
        return DaggerAppComponent.builder().application(this).build();
    }
}
