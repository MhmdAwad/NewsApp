package com.mhmdawad.newsapp.di.main.fragment.search;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.mhmdawad.newsapp.di.ViewModelKey;
import com.mhmdawad.newsapp.network.main.MainApi;
import com.mhmdawad.newsapp.paging.main.category.CategoryDataSourceFactory;
import com.mhmdawad.newsapp.paging.main.search.SearchDataSourceFactory;
import com.mhmdawad.newsapp.ui.main.fragment.category.CategoryItemAdapter;
import com.mhmdawad.newsapp.ui.main.fragment.category.CategoryMainAdapter;
import com.mhmdawad.newsapp.ui.main.fragment.category.CategoryViewModel;
import com.mhmdawad.newsapp.ui.main.fragment.search.SearchAdapter;
import com.mhmdawad.newsapp.ui.main.fragment.search.SearchViewModel;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import io.reactivex.disposables.CompositeDisposable;

@Module
public abstract class SearchModule {


    @SearchScope
    @Provides
    static LinearLayoutManager provideVerticalLayoutManager(Application application) {
        return new LinearLayoutManager(application, LinearLayoutManager.VERTICAL, false);
    }

    @Provides
    @SearchScope
    static SearchDataSourceFactory provideSearchDataSourceFactory(CompositeDisposable disposable , MainApi mainApi) {
        return new SearchDataSourceFactory(mainApi, disposable);
    }

    @SearchScope
    @Provides
    static SearchAdapter provideSearchAdapter(RequestManager requestManager, @Named("defaultRequestOption") RequestOptions requestOptions) {
        return new SearchAdapter(requestManager, requestOptions);
    }

    @Binds
    @IntoMap
    @SearchScope
    @ViewModelKey(SearchViewModel.class)
    abstract ViewModel bindViewModel(SearchViewModel categoryViewModel);
}
