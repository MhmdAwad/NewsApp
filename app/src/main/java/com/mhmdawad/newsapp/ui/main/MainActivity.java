package com.mhmdawad.newsapp.ui.main;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.mhmdawad.newsapp.R;
import com.mhmdawad.newsapp.databinding.ActivityMainBinding;
import com.mhmdawad.newsapp.ui.language.LanguageActivity;
import com.mhmdawad.newsapp.utils.Constants;
import com.mhmdawad.newsapp.viewModels.ViewModelProviderFactory;


import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Named("circleRequestOption")
    @Inject
    RequestOptions requestOptions;

    @Inject
    RequestManager requestManager;

    @Inject
    ViewModelProviderFactory providerFactory;

    private ActivityMainBinding binding;

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
         viewModel = new ViewModelProvider(this, providerFactory).get(MainViewModel.class);

        NavController navController = Navigation.findNavController(this, R.id.navHostFragment);
        NavigationUI.setupWithNavController(binding.mainBottomNav, navController);
        binding.flagImage.setOnClickListener(v -> changeLanguage());
        changeFlag();
    }

    private void changeFlag(){
        viewModel.getCountry().observe(this, countryImage ->
                requestManager.setDefaultRequestOptions(requestOptions).load(countryImage)
                .into(binding.flagImage));

    }
    private void changeLanguage(){
        startActivity(new Intent(this, LanguageActivity.class));
        changeFlag();
    }

}