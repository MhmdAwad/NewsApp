package com.mhmdawad.newsapp.ui.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.mhmdawad.newsapp.database.NewsDao;
import com.mhmdawad.newsapp.models.ArticlesItem;
import com.mhmdawad.newsapp.models.Response;
import com.mhmdawad.newsapp.network.main.MainApi;
import com.mhmdawad.newsapp.utils.Constants;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainRepository {

    private static final String TAG = "MainRepository";
    private MainApi mainApi;
    private MediatorLiveData<MainResource<List<ArticlesItem>>> responseMediatorLiveData = new MediatorLiveData<>();
    private CompositeDisposable disposable;
    private NewsDao newsDao;

    @Inject
    public MainRepository(MainApi mainApi, CompositeDisposable disposable, NewsDao newsDao) {
        this.mainApi = mainApi;
        this.disposable = disposable;
        this.newsDao = newsDao;

        fetchFromApi();
    }


    public LiveData<MainResource<List<ArticlesItem>>> getResponseMediatorLiveData() {
        return responseMediatorLiveData;
    }

    private void fetchFromDB() {

        disposable.add(
                newsDao.getArticles()
                        .observeOn(Schedulers.io())
                        .subscribe(data -> {
                            if (data.isEmpty())
                                responseMediatorLiveData.postValue(MainResource.error("no data in database", null));
                            else
                                responseMediatorLiveData.postValue(MainResource.loaded(data)); })
        );
    }

    private void saveData(Response response) {
        newsDao.deleteArticles();
        newsDao.insertArticles(response.getArticles());
    }


    private void fetchFromApi() {
        responseMediatorLiveData.setValue(MainResource.loading(null));
        disposable.add(
                mainApi.getTopHead("us", 2, Constants.API_KEY)
                        .timeout(1, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .subscribe(data -> {
                                    responseMediatorLiveData.postValue(MainResource.loaded(data.getArticles()));
                                    saveData(data);
                                }, throwable -> fetchFromDB()
                        )
        );
    }

    public void clearDisposable() {
        disposable.clear();
    }

}
