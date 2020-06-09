package com.mhmdawad.newsapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mhmdawad.newsapp.database.saved.SavedDao;
import com.mhmdawad.newsapp.models.ArticlesItem;
import com.mhmdawad.newsapp.models.saved.SavedArticle;

@Database(entities = {ArticlesItem.class , SavedArticle.class}, version = 1, exportSchema = false)
public abstract class NewsDatabase extends RoomDatabase {

    public abstract NewsDao newsDao();
    public abstract SavedDao savedDao();
}
