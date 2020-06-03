package com.mhmdawad.newsapp.di;

import android.app.Application;

import androidx.room.Room;

import com.mhmdawad.newsapp.database.NewsDatabase;
import com.mhmdawad.newsapp.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Provides
    @Singleton
    static Retrofit provideRetrofit(OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    static HttpLoggingInterceptor provideOkHttpInterceptor (){
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Singleton
    @Provides
    static OkHttpClient provideOkHttp(HttpLoggingInterceptor logging){
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }

    @Singleton
    @Provides
    static NewsDatabase provideRoomDatabase(Application application){
        return Room.databaseBuilder(application,
                NewsDatabase.class, Constants.DATABASE_NAME)
                .build();
    }

}
