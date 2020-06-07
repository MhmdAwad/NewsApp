package com.mhmdawad.newsapp.ui.main.fragment.home;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.mhmdawad.newsapp.R;
import com.mhmdawad.newsapp.databinding.RvHomeLayoutBinding;
import com.mhmdawad.newsapp.models.ArticlesItem;

import javax.inject.Inject;

public class HomeAdapter extends PagedListAdapter<ArticlesItem, HomeAdapter.MainViewHolder> {

    private RequestManager requestManager;
    private RequestOptions requestOptions;

    @Inject
    public HomeAdapter(RequestManager requestManager, RequestOptions requestOptions) {
        super(ArticlesItem.CALLBACK);
        this.requestManager = requestManager;
        this.requestOptions = requestOptions;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.rv_home_layout, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        if(getItem(position) != null)
            holder.bind(getItem(position));
    }

    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RvHomeLayoutBinding binding;
        MainViewHolder(@NonNull RvHomeLayoutBinding itemView) {
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
