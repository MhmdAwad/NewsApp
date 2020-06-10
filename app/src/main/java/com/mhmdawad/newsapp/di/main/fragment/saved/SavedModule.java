package com.mhmdawad.newsapp.di.main.fragment.saved;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.mhmdawad.newsapp.di.ViewModelKey;
import com.mhmdawad.newsapp.ui.main.fragment.saved.SavedAdapter;
import com.mhmdawad.newsapp.ui.main.fragment.saved.SavedViewModel;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import io.reactivex.disposables.CompositeDisposable;

@Module
public abstract class SavedModule {


    @SavedScope
    @Provides
    static LinearLayoutManager provideLinearLayoutManager(Application application) {
        return new LinearLayoutManager(application, LinearLayoutManager.VERTICAL, false);
    }

    @SavedScope
    @Provides
    static SavedAdapter provideMainAdapter(RequestManager requestManager, @Named("defaultRequestOption") RequestOptions requestOptions) {
        return new SavedAdapter(requestManager,requestOptions);
    }

    @SavedScope
    @Named("saved")
    @Provides
    static CompositeDisposable provideDisposable() {
        return new CompositeDisposable();
    }


    @Binds
    @IntoMap
    @ViewModelKey(SavedViewModel.class)
    abstract ViewModel bindSavedViewModel(SavedViewModel homeViewModel);
}
