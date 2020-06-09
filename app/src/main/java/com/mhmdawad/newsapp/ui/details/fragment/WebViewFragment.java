package com.mhmdawad.newsapp.ui.details.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mhmdawad.newsapp.R;
import com.mhmdawad.newsapp.databinding.FragmentWebViewBinding;

import org.jetbrains.annotations.NotNull;


public class WebViewFragment extends Fragment {

    private String articleLink;
    public WebViewFragment(String articleLink) {
        this.articleLink = articleLink;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentWebViewBinding binding =
        DataBindingUtil.inflate(inflater,R.layout.fragment_web_view, container, false);

        binding.setArticleLink(articleLink);
        return binding.getRoot();
    }
}
