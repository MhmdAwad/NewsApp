package com.mhmdawad.newsapp.ui.details;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mhmdawad.newsapp.database.saved.SavedDao;
import com.mhmdawad.newsapp.models.ArticlesItem;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
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

    void saveArticle(ArticlesItem articlesItem){
        disposable.add(Single.just(savedDao)
                .subscribeOn(Schedulers.io())
                .subscribe(db -> {
                    Log.d("TAG", "removeDB: SUCCESS" );
                    db.saveArticle(articlesItem);
                    checkArticleExist(articlesItem.getTitle());
                }, throwable -> Log.d("TAG", "removeDB: ERROR" + throwable))
        );
    }

    void deleteSavedArticle(String articleTitle){
        disposable.add(Single.just(savedDao)
                .subscribeOn(Schedulers.io())
                .subscribe(db -> {
                    Log.d("TAG", "removeDB: SUCCESS" );
                    db.deleteSavedArticle(articleTitle);
                    checkArticleExist(articleTitle);
                }, throwable -> Log.d("TAG", "removeDB: ERROR" + throwable))
        );
    }


    void checkArticleExist(String title) {
        disposable.add(
                savedDao.getSpecificSavedArticles(title)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(success -> {
                            articleExist.postValue(true);
                                    Log.d(TAG, "checkArticleExist: success" + success);
                                },
                                error -> {
                            articleExist.postValue(false);
                                    Log.d(TAG, "checkArticleExist: error " + error);
                                })
        );
    }

    LiveData<Boolean> getArticleExist() {
        return articleExist;
    }
}
