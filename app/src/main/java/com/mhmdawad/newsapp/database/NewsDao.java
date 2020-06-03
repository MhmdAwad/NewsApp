package com.mhmdawad.newsapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mhmdawad.newsapp.models.ArticlesItem;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface NewsDao {


    @Insert
    void insertArticles(List<ArticlesItem> articlesList);

    @Query("DELETE From articles_table")
    void deleteArticles();

    @Query("SELECT * From articles_table")
    Single<List<ArticlesItem>> getArticles();
}
