package com.mhmdawad.newsapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mhmdawad.newsapp.models.ArticlesItem;

@Database(entities = ArticlesItem.class, version = 1, exportSchema = false)
public abstract class NewsDatabase extends RoomDatabase {

    public abstract NewsDao newsDao();
}
