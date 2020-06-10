package com.mhmdawad.newsapp.ui.main.fragment.category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.mhmdawad.newsapp.models.ArticlesItem;
import com.mhmdawad.newsapp.paging.main.category.CategoryDataSource;
import com.mhmdawad.newsapp.paging.main.category.CategoryDataSourceFactory;
import com.mhmdawad.newsapp.utils.DataStatus;

import javax.inject.Inject;

public class CategoryViewModel extends ViewModel {

    private LiveData<PagedList<ArticlesItem>> itemPagedList;
    private LiveData<DataStatus> dataStatus;
    private CategoryDataSourceFactory factory;
    private PagedList.Config config;
    private MutableLiveData<Integer> selectedItem;
    private MutableLiveData<ArticlesItem> articleDetails;


    @Inject
    public CategoryViewModel(CategoryDataSourceFactory factory, PagedList.Config config) {
        this.factory = factory;
        this.config = config;
        this.selectedItem = new MutableLiveData<>();
        this.articleDetails = new MutableLiveData<>();
        fetchTopNewsData();
    }

    public void fetchCategoryNewsData(String category, int pos){
        selectedItem.setValue(pos);
        factory.setCategory(category);
        refreshData();
    }

    public void openArticleDetails(ArticlesItem articlesItem){
        articleDetails.setValue(articlesItem);
    }

    private void fetchTopNewsData(){
        itemPagedList = new LivePagedListBuilder(factory, config).build();
        dataStatus = Transformations.switchMap(factory.getMutableLiveData(), CategoryDataSource::getMutableLiveData);
    }

     void refreshData() {
        if (itemPagedList.getValue() != null) {
            itemPagedList.getValue().getDataSource().invalidate();
        }
    }

    void resetArticleDetails(){
        articleDetails.setValue(null);
    }
    LiveData<Integer> getSelectedItem() {
        return selectedItem;
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
