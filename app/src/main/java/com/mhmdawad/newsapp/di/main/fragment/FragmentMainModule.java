package com.mhmdawad.newsapp.di.main.fragment;


import com.mhmdawad.newsapp.di.main.fragment.category.CategoryModule;
import com.mhmdawad.newsapp.di.main.fragment.category.CategoryScope;
import com.mhmdawad.newsapp.di.main.fragment.home.HomeModule;
import com.mhmdawad.newsapp.di.main.fragment.home.HomeScope;
import com.mhmdawad.newsapp.di.main.fragment.saved.SavedModule;
import com.mhmdawad.newsapp.di.main.fragment.saved.SavedScope;
import com.mhmdawad.newsapp.ui.main.fragment.category.CategoryFragment;
import com.mhmdawad.newsapp.ui.main.fragment.saved.SavedFragment;
import com.mhmdawad.newsapp.ui.main.fragment.SearchFragment;
import com.mhmdawad.newsapp.ui.main.fragment.home.HomeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class FragmentMainModule {


    @HomeScope
    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeFragment homeFragment();

    @ContributesAndroidInjector
    abstract SearchFragment searchFragment();


    @CategoryScope
    @ContributesAndroidInjector(modules = CategoryModule.class)
    abstract CategoryFragment categoryFragment();

    @SavedScope
    @ContributesAndroidInjector(modules = SavedModule.class)
    abstract SavedFragment savedFragment();
}
