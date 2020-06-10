package com.mhmdawad.newsapp.ui.main.fragment.search;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.mhmdawad.newsapp.models.ArticlesItem;
import com.mhmdawad.newsapp.paging.main.search.SearchDataSource;
import com.mhmdawad.newsapp.paging.main.search.SearchDataSourceFactory;
import com.mhmdawad.newsapp.utils.DataStatus;

import javax.inject.Inject;

public class SearchViewModel extends ViewModel {

    private LiveData<PagedList<ArticlesItem>> itemPagedList;
    private MutableLiveData<DataStatus> dataStatus;
    private SearchDataSourceFactory factory;
    private PagedList.Config config;
    private MutableLiveData<ArticlesItem> articleDetails;


    @Inject
    public SearchViewModel(SearchDataSourceFactory factory, PagedList.Config config) {
        this.factory = factory;
        this.config = config;
        this.articleDetails = new MutableLiveData<>();
        fetchTopNewsData();
    }

    public void searchArticles(String search){
        Log.d("TAG", "searchArticles: ");
        if(search.equals("") || search.length() == 0)
            dataStatus.setValue(DataStatus.EMPTY);
        else {
            factory.setSearch(search);
            refreshData();
        }
    }

    public void openArticleDetails(ArticlesItem articlesItem){
        articleDetails.setValue(articlesItem);
    }

    private void fetchTopNewsData(){
        itemPagedList = new LivePagedListBuilder(factory, config).build();
        dataStatus = (MutableLiveData<DataStatus>) Transformations.switchMap(factory.getMutableLiveData(), SearchDataSource::getMutableLiveData);
    }

    void refreshData() {
        if (itemPagedList.getValue() != null) {
            itemPagedList.getValue().getDataSource().invalidate();
        }
    }

    void resetArticleDetails(){
        articleDetails.setValue(null);
    }

    LiveData<PagedList<ArticlesItem>> getItemPagedList() {
        return itemPagedList;
    }

    LiveData<DataStatus> getDataStatus() {
        return dataStatus;
    }

    LiveData<ArticlesItem> getArticleDetails() {
        return articleDetails;
    }
}
