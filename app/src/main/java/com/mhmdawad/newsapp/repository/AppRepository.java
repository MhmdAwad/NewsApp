package com.mhmdawad.newsapp.repository;


import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mhmdawad.newsapp.database.NewsDao;
import com.mhmdawad.newsapp.models.ArticlesItem;
import com.mhmdawad.newsapp.models.Country;
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

public class AppRepository {


    private MainApi mainApi;
    private CompositeDisposable disposable;
    private NewsDao newsDao;
    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;
    private MutableLiveData<String> countryImage = new MutableLiveData<>();

    @Inject
    public AppRepository(MainApi mainApi, CompositeDisposable disposable, NewsDao newsDao,
                         SharedPreferences.Editor editor, SharedPreferences preferences) {
        this.mainApi = mainApi;
        this.disposable = disposable;
        this.newsDao = newsDao;
        this.editor = editor;
        this.preferences = preferences;
    }


    private String getSelectedCountryName() {
        try {
            return preferences.getString(Constants.COUNTRY_PREFS_NAME,
                    "us");
        } catch (NullPointerException e) {
            return "us";
        }
    }

    public void getCountryImage() {
        String image = preferences.getString(Constants.COUNTRY_PREFS_IMAGE,
                "https://cdn.countryflags.com/thumbs/united-states-of-america/flag-400.png");
        countryImage.setValue(image);
    }

    public LiveData<String> getSelectedCountryImage() {
        return countryImage;
    }

    public void changeCountry(Country country) {
        editor.putString(Constants.COUNTRY_PREFS_NAME, country.getSmallName());
        editor.putString(Constants.COUNTRY_PREFS_IMAGE, country.getImage());
        editor.apply();
        getCountryImage();
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


    public Flowable<Response> fetchFromApi(int page) {
        return mainApi.getTopHead(getSelectedCountryName(), page, 10, Constants.API_KEY)
                .subscribeOn(Schedulers.io());
    }

}
