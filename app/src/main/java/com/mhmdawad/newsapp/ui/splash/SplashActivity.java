package com.mhmdawad.newsapp.ui.splash;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.mhmdawad.newsapp.R;
import com.mhmdawad.newsapp.ui.main.MainRepository;
import com.mhmdawad.newsapp.ui.main.MainActivity;
import com.mhmdawad.newsapp.utils.Constants;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends DaggerAppCompatActivity {

    @Inject
    @Named("splashDisposable")
    CompositeDisposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_splash);
        disposable.add(
                Single.timer(3, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .subscribe(sub -> {
                            startActivity(new Intent(this.getApplicationContext(), MainActivity.class));
                            finish();
                        })
        );
    }

}
