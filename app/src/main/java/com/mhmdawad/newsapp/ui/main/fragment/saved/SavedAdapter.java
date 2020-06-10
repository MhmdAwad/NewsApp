package com.mhmdawad.newsapp.ui.main.fragment.saved;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.mhmdawad.newsapp.R;
import com.mhmdawad.newsapp.databinding.RvSavedLayoutBinding;
import com.mhmdawad.newsapp.models.saved.SavedArticle;
import com.mhmdawad.newsapp.ui.main.MainRepository;

import java.util.ArrayList;
import java.util.List;


public class SavedAdapter extends RecyclerView.Adapter<SavedAdapter.SavedViewHolder> {

    private RequestManager requestManager;
    private RequestOptions requestOptions;
    private List<SavedArticle> savedAdapterList;
    private MainRepository mainRepository;
    private SavedViewModel viewModel;

    public SavedAdapter(RequestManager requestManager, RequestOptions requestOptions) {
        this.requestManager = requestManager;
        this.requestOptions = requestOptions;
        this.mainRepository = mainRepository;
        savedAdapterList = new ArrayList<>();
    }

    @NonNull
    @Override
    public SavedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SavedViewHolder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.rv_saved_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SavedViewHolder holder, int position) {
        holder.bind(savedAdapterList.get(position));
    }

    @Override
    public int getItemCount() {
        return savedAdapterList.size();
    }

    class SavedViewHolder extends RecyclerView.ViewHolder {
        private RvSavedLayoutBinding binding;
        SavedViewHolder(@NonNull RvSavedLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(SavedArticle article){
            binding.setArticle(article);
            binding.setReqManager(requestManager.setDefaultRequestOptions(requestOptions));
            binding.setViewModel(viewModel);
            binding.executePendingBindings();
        }
    }

    void setAdapterList(List<SavedArticle> savedAdapters){
        this.savedAdapterList = savedAdapters;
        notifyDataSetChanged();
    }

    void setViewModel(SavedViewModel viewModel){
        this.viewModel = viewModel;
    }
}
