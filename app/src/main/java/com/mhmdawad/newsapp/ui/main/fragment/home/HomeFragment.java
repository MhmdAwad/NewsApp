package com.mhmdawad.newsapp.ui.main.fragment.home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.snackbar.Snackbar;
import com.mhmdawad.newsapp.R;
import com.mhmdawad.newsapp.databinding.FragmentHomeBinding;
import com.mhmdawad.newsapp.ui.language.LanguageActivity;
import com.mhmdawad.newsapp.utils.Constants;
import com.mhmdawad.newsapp.viewModels.ViewModelProviderFactory;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.DaggerFragment;

public class HomeFragment extends DaggerFragment {


    private HomeViewModel viewModel;
    private FragmentHomeBinding binding;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    LinearLayoutManager layoutManager;

    @Inject
    HomeAdapter adapter;

    @Named("circleRequestOption")
    @Inject
    RequestOptions requestOptions;

    @Inject
    RequestManager requestManager;

    @Inject
    ConnectivityManager connectivityManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, providerFactory).get(HomeViewModel.class);
        viewModel.fetchData();
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setReqManager(requestManager.setDefaultRequestOptions(requestOptions));
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecyclerView();
        initRefreshListeners();
        observeObservers();
        changeFlag();
        binding.flagImage.setOnClickListener(this::changeLanguage);

    }

    private void changeLanguage(View view) {
        if(Constants.isConnected(connectivityManager)){
            Intent intent = new Intent(getActivity(), LanguageActivity.class);
            startActivityForResult(intent, 101);
        } else{
            Snackbar.make(view, "You are offline", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(Color.RED).show();
        }
    }

    private void changeFlag() {
        viewModel.getCountry().observe(getViewLifecycleOwner(), countryImage -> {
            binding.setFlagUrl(countryImage);
            binding.setReqManager(requestManager.setDefaultRequestOptions(requestOptions.error(R.drawable.ic_location)));
            binding.executePendingBindings();
        });
    }
    private void initRefreshListeners(){
        binding.swipeRefresh.setOnRefreshListener(() -> viewModel.refreshData());
        binding.tryAgain.setOnClickListener(v -> viewModel.refreshData());
    }
    private void initRecyclerView() {
        binding.mainRV.setLayoutManager(layoutManager);
        binding.mainRV.setAdapter(adapter);
    }

    private void stopSwipeRefresh(){
        binding.swipeRefresh.setRefreshing(false);
    }

    private void stopShimmer(){
        binding.shimmerLayout.stopShimmer();
        binding.shimmerLayout.setVisibility(View.GONE);
    }


    private void observeObservers() {
        viewModel.getItemPagedList().observe(getViewLifecycleOwner(), articlesItems -> {
            if(articlesItems != null) {
                adapter.submitList(articlesItems);
                binding.mainRV.scrollToPosition(0);
            }
        });

        viewModel.getDataStatus().observe(getViewLifecycleOwner(), articlesItems -> {
            if (articlesItems != null) {
                switch (articlesItems) {
                    case ERROR:
                        stopSwipeRefresh();
                        stopShimmer();
                        binding.mainRV.setVisibility(View.INVISIBLE);
                        binding.noInternetContainer.setVisibility(View.VISIBLE);
                        break;
                    case LOADING:
                        binding.shimmerLayout.startShimmer();
                        break;
                    case LOADED:
                        stopSwipeRefresh();
                        stopShimmer();
                        binding.mainRV.setVisibility(View.VISIBLE);
                        binding.noInternetContainer.setVisibility(View.GONE);
                }
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
                viewModel.refreshData();
            }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.getCountry().removeObservers(getViewLifecycleOwner());
        viewModel.changedCountry().removeObservers(getViewLifecycleOwner());
        binding.mainRV.setLayoutManager(null);
        binding.mainRV.setAdapter(null);
        adapter.submitList(null);
        binding = null;

    }
}
