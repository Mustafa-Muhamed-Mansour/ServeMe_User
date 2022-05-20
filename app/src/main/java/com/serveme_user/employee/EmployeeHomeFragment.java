package com.serveme_user.employee;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.serveme_user.R;
import com.serveme_user.adapter.EmployeeAdapter;
import com.serveme_user.databinding.FragmentEmployeeHomeBinding;
import com.serveme_user.model.EmployeeModel;

import java.util.ArrayList;

public class EmployeeHomeFragment extends Fragment
{

    private NavController navController;
    private FragmentEmployeeHomeBinding binding;

    private ArrayList<EmployeeModel> employeeModels;
    private EmployeeAdapter employeeAdapter;

    private DatabaseReference retriveRef;

    private String JOB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentEmployeeHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);


        initVeiws(view);
        clickedViews();
        initDatabase();
        retriveData();

    }

    private void initVeiws(View view)
    {
        navController = Navigation.findNavController(view);
        employeeModels = new ArrayList<>();
        employeeAdapter = new EmployeeAdapter(employeeModels);
        binding.rVEmployee.setAdapter(employeeAdapter);
        binding.rVEmployee.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        binding.rVEmployee.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    private void clickedViews()
    {
        binding.imgBackEmployee.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                navController.navigate(R.id.action_employeeHomeFragment_to_homeFragment);
            }
        });
    }

    private void initDatabase()
    {
        retriveRef = FirebaseDatabase.getInstance().getReference();
    }

    private void retriveData()
    {
        EmployeeHomeFragmentArgs job = EmployeeHomeFragmentArgs.fromBundle(getArguments());
        JOB = job.getJob();
        retriveRef
                .child("Jobs")
                .child(JOB)
                .addValueEventListener(new ValueEventListener()
                {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {

                        for (DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            employeeModels.clear();
                            EmployeeModel model = dataSnapshot.getValue(EmployeeModel.class);
                            employeeModels.add(model);
                        }
                        employeeAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error)
                    {
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}