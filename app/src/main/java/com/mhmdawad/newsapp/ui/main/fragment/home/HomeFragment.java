package com.mhmdawad.newsapp.ui.main.fragment.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.mhmdawad.newsapp.R;
import com.mhmdawad.newsapp.databinding.FragmentHomeBinding;
import com.mhmdawad.newsapp.models.Country;
import com.mhmdawad.newsapp.viewModels.ViewModelProviderFactory;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.DaggerFragment;


public class HomeFragment extends DaggerFragment {

    private HomeViewModel viewModel;
    private static final String TAG = "HomeFragment";
    private FragmentHomeBinding binding;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    LinearLayoutManager layoutManager;

    @Inject
    HomeAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this, providerFactory).get(HomeViewModel.class);
        initRecyclerView();
        observeObservers();
    }

    private void initRecyclerView() {
        binding.mainRV.setLayoutManager(layoutManager);
        binding.mainRV.setAdapter(adapter);
    }

    private void observeObservers() {
        viewModel.getResponseData().observe(getViewLifecycleOwner(), responseMainResource -> {
            if (responseMainResource != null) {
                switch (responseMainResource.status) {
                    case ERROR:
                        Log.d(TAG, "observeObservers: NO DATA ERROR");
                        break;
                    case LOADING:
                        Log.d(TAG, "observeObservers: LOADING...");
                        break;
                    case LOADED:
                        Log.d(TAG, "observeObservers:  LOADED");
                        adapter.addList(responseMainResource.data);
                        binding.mainRV.scrollToPosition(0);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.mainRV.setLayoutManager(null);
        binding.mainRV.setAdapter(null);
    }
}
