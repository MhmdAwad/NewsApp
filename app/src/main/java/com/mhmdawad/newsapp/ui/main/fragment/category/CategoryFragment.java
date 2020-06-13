package com.mhmdawad.newsapp.ui.main.fragment.category;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.mhmdawad.newsapp.R;
import com.mhmdawad.newsapp.ViewModelProviderFactory;
import com.mhmdawad.newsapp.databinding.FragmentCategoryBinding;
import com.mhmdawad.newsapp.models.ArticlesItem;
import com.mhmdawad.newsapp.ui.details.DetailsActivity;
import com.mhmdawad.newsapp.ui.main.fragment.home.HomeAdapter;
import com.mhmdawad.newsapp.utils.Constants;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.DaggerFragment;

public class CategoryFragment extends DaggerFragment {

    @Inject
    @Named("vertical")
    LinearLayoutManager verticalLayoutManager;
    @Inject
    @Named("horizontal")
    LinearLayoutManager horizontalLayoutManager;
    @Inject
    CategoryItemAdapter categoryItemAdapter;
    @Inject
    CategoryMainAdapter mainAdapter;
    @Inject
    ViewModelProviderFactory providerFactory;

    private CategoryViewModel viewModel;
    private FragmentCategoryBinding binding;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false);
        viewModel = new ViewModelProvider(this, providerFactory).get(CategoryViewModel.class);

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

        viewModel.getSelectedItem().observe(getViewLifecycleOwner(), position -> {
            binding.categoryItemsRV.scrollToPosition(position);
            categoryItemAdapter.setPositionNum(position);
        });

        viewModel.getItemPagedList().observe(getViewLifecycleOwner(), articles -> {
            mainAdapter.submitList(null);
            mainAdapter.submitList(articles);
            binding.categoryRV.scrollToPosition(0);
        });


        viewModel.getDataStatus().observe(getViewLifecycleOwner(), dataStatus -> {
            switch (dataStatus) {
                case LOADED:
                    stopSwipeRefresh();
                    binding.categoryRV.setVisibility(View.VISIBLE);
                    binding.noInternetContainer.getRoot().setVisibility(View.GONE);
                    break;
                case LOADING:
                    binding.swipeRefresh.setRefreshing(true);
                    binding.noInternetContainer.getRoot().setVisibility(View.GONE);
                    break;
                case ERROR:
                    stopSwipeRefresh();
                    binding.categoryRV.setVisibility(View.GONE);
                    binding.noInternetContainer.getRoot().setVisibility(View.VISIBLE);
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
        binding.categoryRV.setLayoutManager(verticalLayoutManager);
        binding.categoryRV.setAdapter(mainAdapter);
        binding.categoryItemsRV.setLayoutManager(horizontalLayoutManager);
        binding.categoryItemsRV.setAdapter(categoryItemAdapter);
        categoryItemAdapter.setViewModel(viewModel);
        mainAdapter.setViewModel(viewModel);

        binding.categoryRV.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if(scrollY > oldScrollY)
                binding.categoryItemsRV.animate().translationY(-1000).setInterpolator(new AccelerateInterpolator()).start();
            else if(scrollY < oldScrollY || scrollY == 0)
                binding.categoryItemsRV.animate().translationY(0).setInterpolator(new AccelerateInterpolator()).start();
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.resetArticleDetails();
    }


}
