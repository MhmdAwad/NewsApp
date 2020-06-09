package com.mhmdawad.newsapp.ui.language;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.mhmdawad.newsapp.R;
import com.mhmdawad.newsapp.databinding.ActivityLanguageBinding;
import com.mhmdawad.newsapp.ui.main.MainRepository;
import com.mhmdawad.newsapp.viewModels.ViewModelProviderFactory;


import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;


public class LanguageActivity extends DaggerAppCompatActivity  {

    @Inject
    LanguageAdapter adapter;

    @Inject
    GridLayoutManager gridLayoutManager;

    @Inject
    ViewModelProviderFactory providerFactory;

    private ActivityLanguageBinding binding;
    private LanguageViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_language);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this, providerFactory).get(LanguageViewModel.class);
        initRecyclerView();
        viewsListener();
        observeObservers();
    }

    private void viewsListener(){
        binding.backButton.setOnClickListener(v -> finish());
    }

    private void initRecyclerView() {
        binding.languageRV.setLayoutManager(gridLayoutManager);
        adapter.setViewModel(viewModel);
        binding.languageRV.setAdapter(adapter);
    }

    private void observeObservers(){
        viewModel.getIsCountryChanged().observe(this, isCountryChanged -> {
            if(isCountryChanged)
                returnCountryChanged();
        });
    }

    private void returnCountryChanged(){
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

}
