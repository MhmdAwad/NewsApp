package com.mhmdawad.newsapp.ui.language;

import android.view.LayoutInflater;
import android.view.View;
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
import com.mhmdawad.newsapp.utils.RecyclerViewClickListener;

import java.util.List;

import javax.inject.Inject;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>{


    private List<Country> countryList;
    private RequestManager requestManager;
    private RequestOptions requestOptions;
    private RecyclerViewClickListener clickListener;


    @Inject
    public LanguageAdapter(RequestManager requestManager, RequestOptions requestOptions) {
        this.requestManager = requestManager;
        this.requestOptions = requestOptions;
        this.countryList = Constants.getCountries();
    }

    void addListener(RecyclerViewClickListener recyclerViewClickListener){
        this.clickListener = recyclerViewClickListener;
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

    class LanguageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RvLanguageLayoutBinding binding;
        LanguageViewHolder(@NonNull RvLanguageLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onCountryChanged(countryList.get(getAdapterPosition()));
        }
        void bind(Country country){
            binding.setCountryImage(country.getImage());
            binding.setCountryName(country.getName());
            binding.setReqManager(requestManager.setDefaultRequestOptions(requestOptions));
            binding.executePendingBindings();
        }
    }
}
