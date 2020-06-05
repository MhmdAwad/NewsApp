package com.mhmdawad.newsapp.repository;


import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.mhmdawad.newsapp.database.NewsDao;
import com.mhmdawad.newsapp.models.ArticlesItem;
import com.mhmdawad.newsapp.models.Country;
import com.mhmdawad.newsapp.models.Response;
import com.mhmdawad.newsapp.network.main.MainApi;
import com.mhmdawad.newsapp.ui.main.MainResource;
import com.mhmdawad.newsapp.utils.Constants;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class AppRepository {

    private MainApi mainApi;
    private MediatorLiveData<MainResource<List<ArticlesItem>>> responseMediatorLiveData = new MediatorLiveData<>();
    private CompositeDisposable disposable;
    private NewsDao newsDao;
    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;
    private MutableLiveData<String> countryImage = new MutableLiveData<>();

    @Inject
    public AppRepository(MainApi mainApi, CompositeDisposable disposable, NewsDao newsDao,SharedPreferences.Editor editor,
                         SharedPreferences preferences) {
        this.mainApi = mainApi;
        this.disposable = disposable;
        this.newsDao = newsDao;
        this.editor = editor;
        this.preferences = preferences;
    }

    public LiveData<MainResource<List<ArticlesItem>>> getResponseMediatorLiveData() {
        return responseMediatorLiveData;
    }

    private String getSelectedCountryName() {
        try {
            return preferences.getString(Constants.COUNTRY_PREFS_NAME,
                    "us");
        } catch (NullPointerException e) {
            return "us";
        }
    }

    private void getCountryImage() {
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
        fetchFromApi();
    }


    private void fetchFromDB() {
        disposable.add(
                newsDao.getArticles()
                        .observeOn(Schedulers.io())
                        .subscribe(data -> {
                            if (data.isEmpty())
                                responseMediatorLiveData.postValue(MainResource.error("no data in database", null));
                            else
                                responseMediatorLiveData.postValue(MainResource.loaded(data));
                        })
        );
    }

    private void saveData(Response response) {
        newsDao.deleteArticles();
        newsDao.insertArticles(response.getArticles());
    }

    public void fetchData(){
        fetchFromApi();
        getCountryImage();
    }

    private void fetchFromApi() {
        responseMediatorLiveData.setValue(MainResource.loading(null));
        disposable.add(
                mainApi.getTopHead(getSelectedCountryName(), 2, Constants.API_KEY)
                        .timeout(2, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .subscribe(data -> {
                                    responseMediatorLiveData.postValue(MainResource.loaded(data.getArticles()));
                                    saveData(data);
                                }, throwable -> fetchFromDB()
                        )
        );
    }

}
