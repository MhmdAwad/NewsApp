package com.mhmdawad.newsapp.ui.main;


import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
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
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainRepository {

    private MainApi mainApi;
    private CompositeDisposable disposable;
    private NewsDao newsDao;
    private SharedPreferences preferences;
    private MutableLiveData<String> countryImage ;

    @Inject
    public MainRepository(MainApi mainApi, CompositeDisposable disposable, NewsDao newsDao,
                          SharedPreferences preferences) {
        this.mainApi = mainApi;
        this.disposable = disposable;
        this.newsDao = newsDao;
        this.preferences = preferences;
        countryImage = new MutableLiveData<>();
    }


    private String getSelectedCountryName() {
        try {
            return preferences.getString(Constants.COUNTRY_PREFS_NAME,
                    "us");
        } catch (NullPointerException e) {
            return "us";
        }
    }


    public void removeDB() {
        disposable.add(
                Single.just(newsDao)
                        .subscribeOn(Schedulers.io())
                        .subscribe(db -> {
                            Log.d("TAG", "removeDB: SUCCESS" );
                            db.deleteArticles();
                        }, throwable -> Log.d("TAG", "removeDB: ERROR" + throwable))
        );
    }

    public Flowable<List<ArticlesItem>> fetchFromDB(int size, int offset) {
        return newsDao.getArticles(size, offset)
                .observeOn(Schedulers.io());
    }

    public void saveData(Response response) {
        newsDao.insertArticles(response.getArticles());
    }


    public Flowable<Response> fetchFromApi(int page, int size) {
        return mainApi.getTopHead(getSelectedCountryName(), page, size, Constants.API_KEY)
                .timeout(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io());
    }


    public void getCountryImage() {
        String image = preferences.getString(Constants.COUNTRY_PREFS_IMAGE,
                "https://cdn.countryflags.com/thumbs/united-states-of-america/flag-400.png");
        countryImage.setValue(image);
    }

    public LiveData<String> getSelectedCountryImage() {
        return countryImage;
    }

}
