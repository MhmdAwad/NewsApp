package com.mhmdawad.newsapp.di.main.fragment;

import com.mhmdawad.newsapp.di.ActivityViewModelModule;
import com.mhmdawad.newsapp.di.main.fragment.home.HomeModule;
import com.mhmdawad.newsapp.di.main.fragment.home.HomeScope;
import com.mhmdawad.newsapp.ui.main.fragment.CategoryFragment;
import com.mhmdawad.newsapp.ui.main.fragment.SavedFragment;
import com.mhmdawad.newsapp.ui.main.fragment.SearchFragment;
import com.mhmdawad.newsapp.ui.main.fragment.home.HomeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class FragmentMainModule {


    @HomeScope
    @ContributesAndroidInjector(
            modules = {
                    HomeModule.class,

            }
    )
    abstract HomeFragment homeFragment();

    @ContributesAndroidInjector
    abstract SearchFragment searchFragment();

    @ContributesAndroidInjector
    abstract CategoryFragment categoryFragment();

    @ContributesAndroidInjector
    abstract SavedFragment savedFragment();
}
