package com.mhmdawad.newsapp.ui.main.fragment.category;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mhmdawad.newsapp.R;
import com.mhmdawad.newsapp.databinding.RvCategoryLayoutBinding;
import com.mhmdawad.newsapp.utils.Constants;

import java.util.List;

public class CategoryItemAdapter extends RecyclerView.Adapter<CategoryItemAdapter.CategoryViewHolder> {

    private CategoryViewModel viewModel;
    private List<Pair<String, Integer>> articleCategoryList;
    private int selectedItem;

    public CategoryItemAdapter() {
        articleCategoryList = Constants.getCategoryList();
        selectedItem = 0;
    }

    @NonNull
    @Override
    public CategoryItemAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.rv_category_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemAdapter.CategoryViewHolder holder, int position) {
        holder.bind(articleCategoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return articleCategoryList.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        private RvCategoryLayoutBinding binding;

        CategoryViewHolder(@NonNull RvCategoryLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Pair<String, Integer> category) {
            binding.setViewModel(viewModel);
            binding.setCategory(category);
            binding.setPosition(getAdapterPosition());
            binding.setSelected(selectedItem);
            binding.executePendingBindings();
        }
    }

    void setViewModel(CategoryViewModel viewModel) {
        this.viewModel = viewModel;
    }

    void setPositionNum(int pos){
        this.selectedItem = pos;
        notifyDataSetChanged();
    }

}
