package com.serveme_user.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.serveme_user.databinding.ItemWorkBinding;
import com.serveme_user.model.WorkModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.WorkViewHolder>
{

    private ArrayList<WorkModel> workModels;

    public WorkAdapter(ArrayList<WorkModel> workModels)
    {
        this.workModels = workModels;
    }

    @NonNull
    @Override
    public WorkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new WorkViewHolder(ItemWorkBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WorkViewHolder holder, int position)
    {
        WorkModel model = workModels.get(position);
        Picasso
                .get()
                .load(model.getImage())
                .into(holder.binding.itemImgWork);

        holder.binding.itemTxtTitleWork.setText(model.getTitle());
    }

    @Override
    public int getItemCount()
    {
        return workModels.size();
    }

    public class WorkViewHolder extends RecyclerView.ViewHolder
    {

        private ItemWorkBinding binding;

        public WorkViewHolder(@NonNull ItemWorkBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
