package com.mhmdawad.newsapp.di.splash;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class SplashModule {

    @SplashScope
    @Provides
    @Named("splashDisposable")
    static CompositeDisposable provideDisposable() {
        return new CompositeDisposable();
    }
}
