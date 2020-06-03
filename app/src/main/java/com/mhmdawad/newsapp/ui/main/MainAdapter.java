package com.mhmdawad.newsapp.ui.main;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mhmdawad.newsapp.R;
import com.mhmdawad.newsapp.models.ArticlesItem;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private List<ArticlesItem> articlesList = new ArrayList<>();

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_main_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.textView.setText(articlesList.get(position).getDescription());
        Log.d("TAG", "onBindViewHolder: " + articlesList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }

    void addList(List<ArticlesItem> itemList){
        articlesList.addAll(itemList);
        notifyDataSetChanged();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        TextView textView ;
        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.descTextView);
        }
    }
}
