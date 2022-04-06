package com.serveme_user.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.serveme_user.R;
import com.serveme_user.databinding.ItemEmployeeBinding;
import com.serveme_user.model.EmployeeModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>
{

    private ArrayList<EmployeeModel> employeeModels;

    public EmployeeAdapter(ArrayList<EmployeeModel> employeeModels)
    {
        this.employeeModels = employeeModels;
    }

    private SharedPreferences sharedPreferences;
    public static final String KEY_SHARED_PREF = "myPref";

//    private int count = 2;
//
//    public EmployeeAdapter(ArrayList<EmployeeModel> employeeModels, int count) {
//        this.employeeModels = employeeModels;
//        this.count = count;
//    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        sharedPreferences = parent.getContext().getSharedPreferences(KEY_SHARED_PREF, Context.MODE_PRIVATE);
        return new EmployeeViewHolder(ItemEmployeeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position)
    {
        EmployeeModel model = employeeModels.get(position);

        Picasso
                .get()
                .load(model.getImage())
                .into(holder.binding.itemImgEmployee);
        holder.binding.itemTxtEmployee.setText(model.getFirstName() + " " + model.getLastName());

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("ID_Employee", model.getId());
                editor.apply();
//                editor.commit();
//                Toast.makeText(holder.itemView.getContext(), "ID = " + model.getId(), Toast.LENGTH_SHORT).show();
                Bundle dataEmployee = new Bundle();
                dataEmployee.putString("employeeID", model.getId());
                dataEmployee.putString("employeeFN", model.getFirstName());
                dataEmployee.putString("employeeLN", model.getLastName());
//                dataBundle.putString("employeeGender", model.getGender());
                dataEmployee.putString("employeeJob", model.getJob());
                dataEmployee.putString("employeeImage", model.getImage());
                dataEmployee.putString("employeePhoneNumber", model.getPhoneNumber());
                dataEmployee.putString("employeeEmail", model.getEmail());
                Navigation.findNavController(view).navigate(R.id.action_employeeHomeFragment_to_employeeDetailFragment, dataEmployee);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return employeeModels.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder
    {

        private ItemEmployeeBinding binding;

        public EmployeeViewHolder(@NonNull ItemEmployeeBinding binding)
        {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
