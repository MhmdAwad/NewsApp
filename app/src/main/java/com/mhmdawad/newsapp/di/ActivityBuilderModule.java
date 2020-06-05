package com.mhmdawad.newsapp.di;

import com.mhmdawad.newsapp.di.language.LanguageModule;
import com.mhmdawad.newsapp.di.main.MainModule;
import com.mhmdawad.newsapp.di.main.MainScope;
import com.mhmdawad.newsapp.di.main.fragment.FragmentMainModule;
import com.mhmdawad.newsapp.ui.main.MainActivity;
import com.mhmdawad.newsapp.ui.language.LanguageActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @MainScope
    @ContributesAndroidInjector(modules = {
            MainModule.class,
            FragmentMainModule.class,

    })
    abstract MainActivity mainActivityInject();

    @ContributesAndroidInjector(
            modules = {
                    LanguageModule.class,
            }
    )
    abstract LanguageActivity languageActivity();




}
