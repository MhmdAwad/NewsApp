package com.mhmdawad.newsapp.paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.mhmdawad.newsapp.models.ArticlesItem;
import com.mhmdawad.newsapp.ui.main.MainRepository;
import com.mhmdawad.newsapp.utils.DataStatus;


import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class NewsDataSource extends PageKeyedDataSource<Integer, ArticlesItem> {

    private static final String TAG = "NewsDataSource";
    private CompositeDisposable disposable;
    private MainRepository mainRepository;
    private MutableLiveData<DataStatus> mutableLiveData;

    public LiveData<DataStatus> getMutableLiveData() {
        return mutableLiveData;
    }

    @Inject
    public NewsDataSource(CompositeDisposable disposable, MainRepository mainRepository) {
        this.disposable = disposable;
        this.mainRepository = mainRepository;
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, ArticlesItem> callback) {
        mutableLiveData.postValue(DataStatus.LOADING);
        disposable.add(mainRepository.fetchFromApi(1, params.requestedLoadSize)
                .subscribe(data -> {
                            if (data.getArticles().isEmpty())
                                throw new NullPointerException();

                            callback.onResult(data.getArticles(), null, 2);
                            mutableLiveData.postValue(DataStatus.LOADED);
                            mainRepository.saveData(data);
                        }, throwable ->
                                disposable.add(mainRepository.fetchFromDB(10, 0)
                                        .subscribe(data -> {
                                            if (data.isEmpty())
                                                mutableLiveData.postValue(DataStatus.ERROR);
                                            else {
                                                mutableLiveData.postValue(DataStatus.LOADED);
                                                callback.onResult(data, null, 10);
                                            }
                                        }, error -> Log.d(TAG, "loadInitial: " + error)))
                ));

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ArticlesItem> callback) {
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ArticlesItem> callback) {
        disposable.add(
                mainRepository.fetchFromApi(params.key, params.requestedLoadSize)
                        .subscribe(data -> {
                                    callback.onResult(data.getArticles(), params.key + 1);
                                    mutableLiveData.postValue(DataStatus.LOADED);
                                    mainRepository.saveData(data);
                                }, throwable -> disposable.add(
                                mainRepository.fetchFromDB(10, params.key)
                                        .subscribe(data -> {
                                                    Log.d(TAG, "loadMMM: New Room " + data.size());
                                                    callback.onResult(data, params.key + 10);
                                                    mutableLiveData.postValue(DataStatus.LOADED);
                                                },
                                                throwable1 -> Log.d(TAG, "database  ERROR " + throwable1))
                                )

                        ));
    }
}
