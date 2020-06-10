package com.mhmdawad.newsapp.di.main.fragment.category;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.mhmdawad.newsapp.di.ViewModelKey;
import com.mhmdawad.newsapp.di.main.fragment.home.HomeScope;
import com.mhmdawad.newsapp.network.main.MainApi;
import com.mhmdawad.newsapp.paging.main.category.CategoryDataSourceFactory;
import com.mhmdawad.newsapp.paging.main.home.HomeDataSourceFactory;
import com.mhmdawad.newsapp.ui.main.MainRepository;
import com.mhmdawad.newsapp.ui.main.fragment.category.CategoryItemAdapter;
import com.mhmdawad.newsapp.ui.main.fragment.category.CategoryMainAdapter;
import com.mhmdawad.newsapp.ui.main.fragment.category.CategoryViewModel;
import com.mhmdawad.newsapp.ui.main.fragment.home.HomeAdapter;
import com.mhmdawad.newsapp.ui.main.fragment.home.HomeViewModel;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import io.reactivex.disposables.CompositeDisposable;

@Module
public abstract class CategoryModule {


    @Named("vertical")
    @CategoryScope
    @Provides
    static LinearLayoutManager provideVerticalLayoutManager(Application application) {
        return new LinearLayoutManager(application, LinearLayoutManager.VERTICAL, false);
    }

    @Named("horizontal")
    @CategoryScope
    @Provides
    static LinearLayoutManager provideLinearLayoutManager(Application application) {
        return new LinearLayoutManager(application, LinearLayoutManager.HORIZONTAL, false);
    }

    @Provides
    @CategoryScope
    static CategoryDataSourceFactory provideCategoryDataSourceFactory(CompositeDisposable disposable , MainApi mainApi) {
        return new CategoryDataSourceFactory(mainApi, disposable);
    }

    @CategoryScope
    @Provides
    static CategoryMainAdapter provideMainAdapter(RequestManager requestManager, @Named("defaultRequestOption") RequestOptions requestOptions) {
        return new CategoryMainAdapter(requestManager, requestOptions);
    }

    @CategoryScope
    @Provides
    static CategoryItemAdapter provideCategoryAdapter() {
        return new CategoryItemAdapter();
    }



    @Binds
    @IntoMap
    @CategoryScope
    @ViewModelKey(CategoryViewModel.class)
    abstract ViewModel bindHomeViewModel(CategoryViewModel categoryViewModel);
}
