package com.mhmdawad.newsapp.ui.main;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.mhmdawad.newsapp.R;
import com.mhmdawad.newsapp.databinding.ActivityMainBinding;
import com.mhmdawad.newsapp.utils.Constants;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends DaggerAppCompatActivity {

    private ActivityMainBinding binding;

    @Inject
    CompositeDisposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        binding.setDate(Constants.getTodayDate());

        NavController navController = Navigation.findNavController(this, R.id.navHostFragment);
        NavigationUI.setupWithNavController(binding.mainBottomNav, navController);
    }


    @Override
    protected void onDestroy() {
        binding = null;
        disposable.clear();
        super.onDestroy();
    }
}