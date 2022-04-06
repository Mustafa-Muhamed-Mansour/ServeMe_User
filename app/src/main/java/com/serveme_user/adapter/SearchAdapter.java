package com.serveme_user.adapter;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.serveme_user.databinding.ItemEmployeeBinding;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>
{



    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new SearchViewHolder(ItemEmployeeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position)
    {
    }

    @Override
    public int getItemCount()
    {
        return 0;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder
    {

        private ItemEmployeeBinding binding;

        public SearchViewHolder(@NonNull ItemEmployeeBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
