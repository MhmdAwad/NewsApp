package com.mhmdawad.newsapp.ui.main;


import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mhmdawad.newsapp.R;
import com.mhmdawad.newsapp.viewModels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    private static final String TAG = "MainActivity";
    private MainViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    LinearLayoutManager layoutManager;

    @Inject
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this, providerFactory).get(MainViewModel.class);

        initRecyclerView();
        observeObservers();
    }

    private void observeObservers() {
        viewModel.getResponseData().observe(this, responseMainResource -> {
            if(responseMainResource != null) {
                switch (responseMainResource.status) {
                    case ERROR:
                        Log.d(TAG, "observeObservers: NO DATA ERROR" );
                        break;
                    case LOADING:
                        Log.d(TAG, "observeObservers: LOADING...");
                        break;
                    case LOADED:
                        Log.d(TAG, "observeObservers:  LOADED");
                        adapter.addList(responseMainResource.data);
                }
            }
        });
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.mainRV);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}