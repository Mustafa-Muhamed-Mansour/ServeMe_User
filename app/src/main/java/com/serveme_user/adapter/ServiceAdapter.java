package com.serveme_user.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.serveme_user.R;
import com.serveme_user.databinding.ItemServiceBinding;
import com.serveme_user.model.ServiceModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>
{

    private ArrayList<ServiceModel> serviceModels;

    public ServiceAdapter(ArrayList<ServiceModel> serviceModels)
    {
        this.serviceModels = serviceModels;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new ServiceViewHolder(ItemServiceBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position)
    {
        ServiceModel model = serviceModels.get(position);
        Picasso
                .get()
                .load(model.getImage())
                .into(holder.binding.itemImgService);
        holder.binding.itemTxtService.setText(model.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (model.getName().equals("Carpenter"))
                {
                    Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_employeeHomeFragment);
                }

                if (!model.getName().equals("Carpenter"))
                {
                    Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_employeeHomeFragment);
                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return serviceModels.size();
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder
    {

        private ItemServiceBinding binding;

        public ServiceViewHolder(@NonNull ItemServiceBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
