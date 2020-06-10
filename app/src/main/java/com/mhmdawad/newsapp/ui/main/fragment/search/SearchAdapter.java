package com.mhmdawad.newsapp.ui.main.fragment.search;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.mhmdawad.newsapp.R;
import com.mhmdawad.newsapp.databinding.RvSearchLayoutBinding;
import com.mhmdawad.newsapp.models.ArticlesItem;

public class SearchAdapter extends PagedListAdapter<ArticlesItem, SearchAdapter.MainViewHolder> {

    private RequestManager requestManager;
    private RequestOptions requestOptions;
    private SearchViewModel viewModel;

    public SearchAdapter(RequestManager requestManager, RequestOptions requestOptions) {
        super(ArticlesItem.CALLBACK);
        this.requestManager = requestManager;
        this.requestOptions = requestOptions;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.rv_search_layout, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        if (getItem(position) != null)
            holder.bind(getItem(position));
    }

    class MainViewHolder extends RecyclerView.ViewHolder {
        private RvSearchLayoutBinding binding;

        MainViewHolder(@NonNull RvSearchLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        void bind(ArticlesItem article) {
            binding.setArticlesItem(article);
            binding.setRequestManage(requestManager.setDefaultRequestOptions(requestOptions));
            binding.setViewModel(viewModel);
            binding.setPosition(getAdapterPosition());
            binding.executePendingBindings();
        }

    }

    public void setViewModel(SearchViewModel viewModel) {
        Log.d("TAG", "setViewModel: ");
        this.viewModel = viewModel;
        notifyDataSetChanged();
    }
}
