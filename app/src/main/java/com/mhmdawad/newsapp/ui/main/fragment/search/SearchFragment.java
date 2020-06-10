package com.mhmdawad.newsapp.ui.main.fragment.search;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mhmdawad.newsapp.R;
import com.mhmdawad.newsapp.ViewModelProviderFactory;
import com.mhmdawad.newsapp.databinding.FragmentSearchBinding;
import com.mhmdawad.newsapp.models.ArticlesItem;
import com.mhmdawad.newsapp.ui.details.DetailsActivity;
import com.mhmdawad.newsapp.utils.Constants;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class SearchFragment extends DaggerFragment {

    @Inject
    LinearLayoutManager layoutManager;
    @Inject
    SearchAdapter searchAdapter;
    @Inject
    ViewModelProviderFactory providerFactory;

    private SearchViewModel viewModel;
    private FragmentSearchBinding binding;
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        viewModel = new ViewModelProvider(this, providerFactory).get(SearchViewModel.class);
        binding.setViewModel(viewModel);

        initRecyclerView();
        initRefreshListeners();
        observeObservers();
        return binding.getRoot();
    }

    private void stopSwipeRefresh() {
        binding.swipeRefresh.setRefreshing(false);
    }

    private void initRefreshListeners() {
        binding.swipeRefresh.setOnRefreshListener(() -> viewModel.refreshData());
        binding.noInternetContainer.tryAgain.setOnClickListener(v -> viewModel.refreshData());
    }


    private void observeObservers() {

        viewModel.getArticleDetails().observe(getViewLifecycleOwner(), articlesItem -> {
            if (articlesItem != null)
                openArticleDetails(articlesItem);
        });

        viewModel.getItemPagedList().observe(getViewLifecycleOwner(), articles -> {
            searchAdapter.submitList(null);
            searchAdapter.submitList(articles);
            binding.searchRV.scrollToPosition(0);
        });


        viewModel.getDataStatus().observe(getViewLifecycleOwner(), dataStatus -> {
            switch (dataStatus) {
                case LOADED:
                    binding.searchRV.setVisibility(View.VISIBLE);
                    binding.swipeRefresh.setRefreshing(false);
                    binding.noInternetContainer.getRoot().setVisibility(View.GONE);
                    break;
                case LOADING:
                    stopSwipeRefresh();
                    binding.swipeRefresh.setRefreshing(true);
                    binding.noInternetContainer.getRoot().setVisibility(View.GONE);
                    break;
                case ERROR:
                    binding.swipeRefresh.setRefreshing(false);
                    binding.searchRV.setVisibility(View.GONE);
                    binding.noInternetContainer.getRoot().setVisibility(View.VISIBLE);
                    break;
                case EMPTY:
                    Toast.makeText(getActivity(), "Field is empty!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openArticleDetails(ArticlesItem articlesItem) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("article", Constants.convertArticleClass(articlesItem));
        intent.putExtra("source", articlesItem.getSource());
        startActivity(intent);
    }

    private void initRecyclerView() {
        searchAdapter.setViewModel(viewModel);
        binding.searchRV.setLayoutManager(layoutManager);
        binding.searchRV.setAdapter(searchAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.resetArticleDetails();
    }

}
