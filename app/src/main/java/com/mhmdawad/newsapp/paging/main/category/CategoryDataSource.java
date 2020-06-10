package com.mhmdawad.newsapp.paging.main.category;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.mhmdawad.newsapp.models.ArticlesItem;
import com.mhmdawad.newsapp.network.main.MainApi;
import com.mhmdawad.newsapp.ui.main.MainRepository;
import com.mhmdawad.newsapp.utils.Constants;
import com.mhmdawad.newsapp.utils.DataStatus;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class CategoryDataSource extends PageKeyedDataSource<Integer, ArticlesItem> {

    private static final String TAG = "NewsDataSource";
    private CompositeDisposable disposable;
    private MainApi mainApi;
    private String category;
    private MutableLiveData<DataStatus> mutableLiveData;

    public LiveData<DataStatus> getMutableLiveData() {
        return mutableLiveData;
    }

    public CategoryDataSource(CompositeDisposable disposable, MainApi mainApi, String category) {
        Log.d("MMMMMMMMMMMMMMMMM", "CategoryDataSource: ");
        this.disposable = disposable;
        this.mainApi = mainApi;
        this.category = category;
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, ArticlesItem> callback) {
        Log.d(TAG, "loadInitial: ");
        mutableLiveData.postValue(DataStatus.LOADING);
        disposable.add(
                mainApi.getEveryThing(category, 1, params.requestedLoadSize, Constants.API_KEY)
                .subscribe(data -> {
                            if (data.getArticles().isEmpty())
                                throw new NullPointerException();

                            callback.onResult(data.getArticles(), null, 2);
                            mutableLiveData.postValue(DataStatus.LOADED);
                        }, error -> mutableLiveData.postValue(DataStatus.ERROR)
                ));

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ArticlesItem> callback) {
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ArticlesItem> callback) {
        disposable.add(
                mainApi.getEveryThing(category, params.key, params.requestedLoadSize, Constants.API_KEY)
                        .subscribe(data -> {
                                    callback.onResult(data.getArticles(), params.key + 1);
                                    mutableLiveData.postValue(DataStatus.LOADED);
                                }, throwable -> Log.d(TAG, "database  ERROR " + throwable)

                        ));
    }
}
