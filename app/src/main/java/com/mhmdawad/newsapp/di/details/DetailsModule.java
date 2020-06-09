package com.mhmdawad.newsapp.di.details;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.mhmdawad.newsapp.database.saved.SavedDao;
import com.mhmdawad.newsapp.ui.details.DetailsRepository;
import com.mhmdawad.newsapp.ui.details.DetailsRepository_Factory;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
class DetailsModule {

    @Provides
    @DetailsScope
    @Named("croppedRequestOption")
    static RequestOptions provideNonCircleRequestOptions() {
        return RequestOptions.centerInsideTransform();
    }

    @Provides
    @DetailsScope
    static DetailsRepository provideDetailsRepository(SavedDao savedDao, CompositeDisposable disposable){
        return new DetailsRepository(savedDao, disposable);
    }
}
