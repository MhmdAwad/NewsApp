package com.mhmdawad.newsapp.ui.language;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.mhmdawad.newsapp.R;
import com.mhmdawad.newsapp.databinding.RvLanguageLayoutBinding;
import com.mhmdawad.newsapp.models.Country;
import com.mhmdawad.newsapp.utils.Constants;

import java.util.List;

import javax.inject.Inject;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>{


    private List<Country> countryList;
    private RequestManager requestManager;
    private RequestOptions requestOptions;
    private LanguageViewModel viewModel;

    @Inject
    public LanguageAdapter(RequestManager requestManager, RequestOptions requestOptions) {
        this.requestManager = requestManager;
        this.requestOptions = requestOptions;
        this.countryList = Constants.getCountries();
    }

    @NonNull
    @Override
    public LanguageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LanguageViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.rv_language_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageViewHolder holder, int position) {
        holder.bind(countryList.get(position));
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public void setViewModel(LanguageViewModel viewModel) {
        this.viewModel = viewModel;
    }

    class LanguageViewHolder extends RecyclerView.ViewHolder{
        private RvLanguageLayoutBinding binding;
        LanguageViewHolder(@NonNull RvLanguageLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Country country){
            binding.setCountry(country);
            binding.setLanguageViewModel(viewModel);
            binding.setReqManager(requestManager.setDefaultRequestOptions(requestOptions));
            binding.executePendingBindings();
        }
    }



}
