package com.mhmdawad.newsapp.ui.main.fragment.home;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.mhmdawad.newsapp.R;
import com.mhmdawad.newsapp.databinding.RvMainLayoutBinding;
import com.mhmdawad.newsapp.models.ArticlesItem;
import com.mhmdawad.newsapp.utils.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MainViewHolder> {

    private List<ArticlesItem> articlesList = new ArrayList<>();
    private RequestManager requestManager;
    private RequestOptions requestOptions;

    @Inject
    public HomeAdapter(RequestManager requestManager, RequestOptions requestOptions) {
        this.requestManager = requestManager;
        this.requestOptions = requestOptions;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.rv_main_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.bind(articlesList.get(position));
    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }

    void addList(List<ArticlesItem> itemList){
        articlesList.clear();
        articlesList.addAll(itemList);
        notifyDataSetChanged();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RvMainLayoutBinding binding;
        MainViewHolder(@NonNull RvMainLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        void bind(ArticlesItem article){
            binding.setArticlesItem(article);
            binding.setRequestManage(requestManager.setDefaultRequestOptions(requestOptions));
            binding.executePendingBindings();
        }

        @Override
        public void onClick(View v) {
        }
    }
}
