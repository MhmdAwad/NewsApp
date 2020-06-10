package com.mhmdawad.newsapp.ui.main.fragment.saved;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.mhmdawad.newsapp.R;
import com.mhmdawad.newsapp.ViewModelProviderFactory;
import com.mhmdawad.newsapp.databinding.FragmentSavedBinding;
import com.mhmdawad.newsapp.models.ArticlesItem;
import com.mhmdawad.newsapp.models.saved.SavedArticle;
import com.mhmdawad.newsapp.ui.details.DetailsActivity;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class SavedFragment extends DaggerFragment {

    private FragmentSavedBinding binding;
    private SavedViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;
    @Inject
    SavedAdapter adapter;
    @Inject
    LinearLayoutManager layoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, providerFactory).get(SavedViewModel.class);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_saved, container, false);
        binding.setLifecycleOwner(this);
        observeObservers();
        initRecyclerView();

        return binding.getRoot();
    }

    private void initRecyclerView(){
        adapter.setViewModel(viewModel);
        binding.savedRV.setLayoutManager(layoutManager);
        binding.savedRV.setAdapter(adapter);
    }
    private void observeObservers(){
        viewModel.observeSavedArticles().observe(getViewLifecycleOwner(), data-> adapter.setAdapterList(data));
        viewModel.observeArticleDetails().observe(getViewLifecycleOwner(), this::openArticleDetails);
    }

    private void openArticleDetails(SavedArticle savedArticle) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("article", savedArticle);
        intent.putExtra("source", savedArticle.getSource());
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getSavedArticles();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.resetArticleDetails();
    }
}
