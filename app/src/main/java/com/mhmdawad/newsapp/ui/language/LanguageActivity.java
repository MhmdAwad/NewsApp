package com.mhmdawad.newsapp.ui.language;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;

import com.mhmdawad.newsapp.R;
import com.mhmdawad.newsapp.databinding.ActivityLanguageBinding;
import com.mhmdawad.newsapp.models.Country;
import com.mhmdawad.newsapp.utils.Constants;
import com.mhmdawad.newsapp.utils.RecyclerViewClickListener;
import com.mhmdawad.newsapp.viewModels.ViewModelProviderFactory;


import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;



public class LanguageActivity extends DaggerAppCompatActivity implements RecyclerViewClickListener {

    @Inject
    LanguageAdapter adapter;
    @Inject
    GridLayoutManager gridLayoutManager;

    @Inject
    ViewModelProviderFactory providerFactory;

    private LanguageViewModel viewModel;

    private ActivityLanguageBinding binding;
    private static final String TAG = "LanguageFragment";

    public LanguageActivity() {
        Log.d(TAG, "LanguageFragment: WTF !!!!!!!!!!!11");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_language);

        viewModel = new ViewModelProvider(this, providerFactory).get(LanguageViewModel.class);
        initRecyclerView();
        viewsListener();
    }

    private void viewsListener(){
        binding.backButton.setOnClickListener(v -> finish());
    }

    private void initRecyclerView() {
        binding.languageRV.setLayoutManager(gridLayoutManager);
        adapter.addListener(this);
        binding.languageRV.setAdapter(adapter);
    }


    @Override
    public void onCountryChanged(Country country) {
        Log.d(TAG, "onCountryChanged: " + country.getName());
        viewModel.changeCountry(country);
        finish();
    }
}
