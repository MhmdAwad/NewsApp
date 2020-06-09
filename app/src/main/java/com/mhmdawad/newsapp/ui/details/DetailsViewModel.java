package com.mhmdawad.newsapp.ui.details;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mhmdawad.newsapp.database.saved.SavedDao;
import com.mhmdawad.newsapp.models.ArticlesItem;

import javax.inject.Inject;

public class DetailsViewModel extends ViewModel {

    private MutableLiveData<Boolean> closeArticle;
    private MutableLiveData<Boolean> openArticleWebView;
    private MutableLiveData<Boolean> shareArticle;
    private DetailsRepository detailsRepository;

    @Inject
    DetailsViewModel(DetailsRepository detailsRepository) {
        this.detailsRepository = detailsRepository;
        closeArticle = new MutableLiveData<>();
        openArticleWebView = new MutableLiveData<>();
        shareArticle = new MutableLiveData<>();
    }

    public void closeArticle(){
        closeArticle.setValue(true);
    }

    public void openArticleWebView(){
        openArticleWebView.setValue(true);
    }

    public void shareArticle(){
        shareArticle.setValue(true);
    }

    LiveData<Boolean> checkIfArticleExist(String title){
        Log.d("TAG", "checkIfArticleExist: ");
        detailsRepository.checkArticleExist(title);
        return detailsRepository.getArticleExist();
    }

    public void saveArticle(ArticlesItem articlesItem){
        Log.d("MMMMMMM", "saveArticle: ");
        detailsRepository.articleStatus(articlesItem);
    }

    public LiveData<Boolean> getShareArticle() {
        return shareArticle;
    }

    LiveData<Boolean> getCloseArticle() {
        return closeArticle;
    }

    LiveData<Boolean> getOpenArticleWebView() {
        return openArticleWebView;
    }
}
