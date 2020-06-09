package com.mhmdawad.newsapp.ui.details;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mhmdawad.newsapp.database.saved.SavedDao;
import com.mhmdawad.newsapp.models.ArticlesItem;
import com.mhmdawad.newsapp.utils.Constants;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class DetailsRepository {
    private SavedDao savedDao;
    private CompositeDisposable disposable;
    private MutableLiveData<Boolean> articleExist;
    private static final String TAG = "DetailsRepository";

    @Inject
    public DetailsRepository(SavedDao savedDao, CompositeDisposable disposable) {
        this.savedDao = savedDao;
        this.disposable = disposable;
        articleExist = new MutableLiveData<>();
    }


    void articleStatus(ArticlesItem articlesItem){
        disposable.add(
                getDatabase()
                .subscribe(db->{

                    if(db.getSpecificSavedArticles(articlesItem.getTitle()) == 1)
                        db.deleteSavedArticle(articlesItem.getTitle());
                    else
                        db.saveArticle(Constants.convertArticleClass(articlesItem));

                    checkArticleExist(articlesItem.getTitle());
                }, throwable -> Log.d(TAG, "articleStatus: " + throwable))
        );
    }


    void checkArticleExist(String title) {
        disposable.add(articleExistSingle(title)
                .subscribe(success -> {
                            if (success > 0)
                                articleExist.postValue(true);
                            else
                                articleExist.postValue(false);
                        },
                        error -> articleExist.postValue(false))
        );
    }

    private Single<SavedDao> getDatabase() {
        return Single.just(savedDao)
                .subscribeOn(Schedulers.io());
    }

    private Single<Integer> articleExistSingle(String title) {
        return savedDao
                .singleSpecificSavedArticles(title)
                .subscribeOn(Schedulers.io());
    }

    LiveData<Boolean> getArticleExist() {
        return articleExist;
    }
}
