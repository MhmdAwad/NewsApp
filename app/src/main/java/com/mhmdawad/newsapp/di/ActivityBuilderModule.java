package com.mhmdawad.newsapp.di;

import com.mhmdawad.newsapp.di.splash.SplashModule;
import com.mhmdawad.newsapp.di.splash.SplashScope;
import com.mhmdawad.newsapp.di.language.LanguageModule;
import com.mhmdawad.newsapp.di.language.LanguageScope;
import com.mhmdawad.newsapp.di.main.MainModule;
import com.mhmdawad.newsapp.di.main.MainScope;
import com.mhmdawad.newsapp.di.main.fragment.FragmentMainModule;
import com.mhmdawad.newsapp.ui.details.DetailsActivity;
import com.mhmdawad.newsapp.ui.main.MainActivity;
import com.mhmdawad.newsapp.ui.language.LanguageActivity;
import com.mhmdawad.newsapp.ui.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuilderModule {

    @MainScope
    @ContributesAndroidInjector(modules = {
            MainModule.class,
            FragmentMainModule.class,

    })
    abstract MainActivity mainActivityInject();

    @LanguageScope
    @ContributesAndroidInjector(
            modules = {
                    LanguageModule.class,
            }
    )
    abstract LanguageActivity languageActivity();


    @SplashScope
    @ContributesAndroidInjector(modules = SplashModule.class)
    abstract SplashActivity splashActivity();


    @ContributesAndroidInjector
    abstract DetailsActivity detailsActivity();



}
