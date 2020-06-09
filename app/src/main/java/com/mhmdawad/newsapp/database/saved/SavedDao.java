package com.mhmdawad.newsapp.database.saved;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mhmdawad.newsapp.models.ArticlesItem;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface SavedDao {

    @Insert
    void saveArticle(ArticlesItem articlesList);

    @Query("DELETE From saved_table WHERE title =:articleTitle")
    void deleteSavedArticle(String articleTitle);

    @Query("SELECT * From saved_table")
    Flowable<List<ArticlesItem>> getSavedArticles();

    @Query("SELECT COUNT(*) From saved_table WHERE title =:name")
    Flowable<Integer> getSpecificSavedArticles(String name);
}
