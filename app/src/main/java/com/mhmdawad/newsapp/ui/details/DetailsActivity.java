package com.mhmdawad.newsapp.ui.details;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.RequestManager;
import com.mhmdawad.newsapp.R;
import com.mhmdawad.newsapp.databinding.ActivityDetailsBinding;
import com.mhmdawad.newsapp.models.ArticlesItem;
import com.mhmdawad.newsapp.ui.details.fragment.WebViewFragment;
import com.mhmdawad.newsapp.viewModels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class DetailsActivity extends DaggerAppCompatActivity {

    private DetailsViewModel viewModel;
    private ActivityDetailsBinding binding;

    @Inject
    RequestManager requestManager;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        viewModel = new ViewModelProvider(this, providerFactory).get(DetailsViewModel.class);
        ArticlesItem articlesItem = getIntent().getExtras().getParcelable("article");

        binding.setViewModel(viewModel);
        binding.setReqManager(requestManager);
        binding.setArticle(articlesItem);
        viewModel.checkIfArticleExist(articlesItem.getTitle());
        observeObservers(articlesItem.getUrl(), articlesItem.getTitle());

    }

    private void observeObservers(String link, String title) {

        viewModel.checkIfArticleExist(title).observe(this, isExist->{
            if(isExist)
                binding.saveArticle.setImageResource(R.drawable.ic_bookmark_blue);
            else
                binding.saveArticle.setImageResource(R.drawable.ic_bookmark_open);
        });

        viewModel.getShareArticle().observe(this, share -> {
            if (share)
                shareArticle(title, link);
        });

        viewModel.getCloseArticle().observe(this, close -> {
            if (close)
                finish();
        });

        viewModel.getOpenArticleWebView().observe(this, openWebView -> {
            if (openWebView)
                openWebView(link);

        });
    }

    private void openWebView(String link) {
        getSupportFragmentManager().beginTransaction()
                .add(android.R.id.content, new WebViewFragment(link))
                .addToBackStack(null)
                .commit();
    }


    private void shareArticle(String title, String link) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, title + "\n\n" + link);
        startActivity(Intent.createChooser(intent, "Dialog title text"));
    }
}
