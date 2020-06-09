package com.mhmdawad.newsapp.database.saved;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mhmdawad.newsapp.models.ArticlesItem;
import com.mhmdawad.newsapp.models.saved.SavedArticle;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface SavedDao {

    @Insert
    void saveArticle(SavedArticle savedArticle);

    @Query("DELETE From saved_table WHERE title =:articleTitle")
    void deleteSavedArticle(String articleTitle);

    @Query("SELECT * From saved_table")
    Single<List<SavedArticle>> getSavedArticles();

    @Query("SELECT COUNT(*) From saved_table WHERE title =:name")
    Integer getSpecificSavedArticles(String name);

    @Query("SELECT COUNT(*) From saved_table WHERE title =:name")
    Single<Integer> singleSpecificSavedArticles(String name);
}
