package com.mhmdawad.newsapp.ui.main;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.databinding.DataBindingUtil;
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
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this, providerFactory).get(MainViewModel.class);
        binding.setDate(Constants.getTodayDate());
        binding.setReqManager(requestManager.setDefaultRequestOptions(requestOptions));

        NavController navController = Navigation.findNavController(this, R.id.navHostFragment);
        NavigationUI.setupWithNavController(binding.mainBottomNav, navController);
        binding.flagImage.setOnClickListener(v -> changeLanguage());
        changeFlag();
    }

    private void changeFlag() {
        viewModel.getCountry().observe(this, countryImage -> {
            binding.setFlagUrl(countryImage);
            binding.setReqManager(requestManager.setDefaultRequestOptions(requestOptions));
            binding.executePendingBindings();
        });
    }

    private void changeLanguage() {
        startActivity(new Intent(this.getApplicationContext(), LanguageActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.getCountry().removeObservers(this);
    }
}