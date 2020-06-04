package com.mhmdawad.newsapp.ui.main.fragment.home;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.mhmdawad.newsapp.R;
import com.mhmdawad.newsapp.models.ArticlesItem;
import com.mhmdawad.newsapp.utils.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

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
        return new MainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_main_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.articleTitle.setText(articlesList.get(position).getTitle());
        requestManager.setDefaultRequestOptions(requestOptions).load(articlesList.get(position).getUrlToImage()).into(holder.articleImage);
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
        TextView articleTitle;
        ImageView articleImage;
        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            articleImage = itemView.findViewById(R.id.articleImage);
            articleTitle = itemView.findViewById(R.id.articleTitle);
        }

        @Override
        public void onClick(View v) {
        }
    }
}
