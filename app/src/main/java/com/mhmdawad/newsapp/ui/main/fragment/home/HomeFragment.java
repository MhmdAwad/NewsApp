package com.mhmdawad.newsapp.ui.main.fragment.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mhmdawad.newsapp.R;
import com.mhmdawad.newsapp.databinding.FragmentHomeBinding;
import com.mhmdawad.newsapp.viewModels.ViewModelProviderFactory;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, providerFactory).get(HomeViewModel.class);
        viewModel.fetchData();
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecyclerView();
        initRefreshListeners();
        observeObservers();
    }

    private void initRefreshListeners(){
        binding.swipeRefresh.setOnRefreshListener(() -> viewModel.fetchData());
        binding.tryAgain.setOnClickListener(v -> viewModel.fetchData());
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
        viewModel.getResponseData().observe(getViewLifecycleOwner(), responseMainResource -> {
            if (responseMainResource != null) {
                switch (responseMainResource.status) {
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
        binding = null;
    }
}
