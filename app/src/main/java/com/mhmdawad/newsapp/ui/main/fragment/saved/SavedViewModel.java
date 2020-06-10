package com.mhmdawad.newsapp.ui.main.fragment.saved;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mhmdawad.newsapp.database.saved.SavedDao;
import com.mhmdawad.newsapp.models.saved.SavedArticle;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SavedViewModel extends ViewModel {

    private static final String TAG = "SavedViewModel";
    private SavedDao savedDao;
    private CompositeDisposable disposable;
    private MutableLiveData<List<SavedArticle>> savedArticlesLiveData;
    private MutableLiveData<SavedArticle> articleDetails;

    @Inject
    SavedViewModel(SavedDao savedDao,@Named("saved") CompositeDisposable disposable) {
        this.savedDao = savedDao;
        this.disposable = disposable;
        savedArticlesLiveData = new MutableLiveData<>();
        articleDetails = new MutableLiveData<>();
    }

    LiveData<List<SavedArticle>> observeSavedArticles() {
        return savedArticlesLiveData;
    }

    void getSavedArticles(){
        disposable.add(savedDao.getSavedArticles()
                .subscribeOn(Schedulers.io())
                .subscribe(data-> savedArticlesLiveData.postValue(data),
                        throwable -> Log.d(TAG, "getSavedArticles: ")));
    }
    public void openArticleDetails(SavedArticle articlesItem){
        Log.d(TAG, "observeArticleDetails: !!!!!!!!!!!!!!!!S" );
        articleDetails.setValue(articlesItem);
    }

    LiveData<SavedArticle> observeArticleDetails(){
        Log.d(TAG, "observeArticleDetails: !!!!!!!!!!!!!!!!" );
        return articleDetails;
    }

    void resetArticleDetails(){
        articleDetails.setValue(null);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
