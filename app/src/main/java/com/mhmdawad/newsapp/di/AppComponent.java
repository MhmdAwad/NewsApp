package com.mhmdawad.newsapp.di;

import android.app.Application;

import com.mhmdawad.newsapp.BaseApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                AppModule.class,
                ActivityBuilderModule.class,
                ViewModelsModule.class,
                ViewModelFactoryModule.class
// TODO change fragments viewmodel place from app to main
        }
)
public interface AppComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
