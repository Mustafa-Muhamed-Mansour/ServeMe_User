package com.serveme_user.employee;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.serveme_user.R;
import com.serveme_user.adapter.WorkAdapter;
import com.serveme_user.databinding.FragmentEmployeeDetailBinding;
import com.serveme_user.model.RequestModel;
import com.serveme_user.model.WorkModel;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class EmployeeDetailFragment extends Fragment
{

    private NavController navController;
    private FragmentEmployeeDetailBinding binding;

    private String randomKey, id, firstName, lastName, image, job, phoneNumber, email, idRef;

    private ArrayList<WorkModel> workModels;
    private WorkAdapter workAdapter;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference retriiveRef, requestRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentEmployeeDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);


        initViews(view);
        retriveBundle();
        initDatabase();
        retriiveData();
        clickedViews();


    }

    private void initViews(View view)
    {
        navController = Navigation.findNavController(view);
        workModels = new ArrayList<>();
        workAdapter = new WorkAdapter(workModels);
        binding.rVWorkEmployee.setAdapter(workAdapter);
        binding.rVWorkEmployee.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//        binding.rVWorkEmployee.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    private void retriveBundle()
    {
        id = getArguments().getString("employeeID");

        firstName = getArguments().getString("employeeFN");
        binding.txtFnEmployeeDetail.setText(firstName);

        lastName = getArguments().getString("employeeLN");
        binding.txtLnEmployeeDetail.setText(lastName);

//        gender = getArguments().getString("employeeGender");
//        binding.txtGenderEmployeeDetail.setText(gender);

        job = getArguments().getString("employeeJob");
        binding.txtJobEmployeeDetail.setText(job);

        image = getArguments().getString("employeeImage");
        Picasso
                .get()
                .load(image)
                .into(binding.imgEmployeeDetail);

        phoneNumber = getArguments().getString("employeePhoneNumber");

//        email = getArguments().getString("employeeEmail");
//        binding.fabEmail.setTextAlignment(email);
    }

    private void initDatabase()
    {
        randomKey = FirebaseDatabase.getInstance().getReference().push().getKey();
        firebaseAuth = FirebaseAuth.getInstance();
        retriiveRef = FirebaseDatabase.getInstance().getReference();
        requestRef = FirebaseDatabase.getInstance().getReference();
        idRef = FirebaseDatabase.getInstance().getReference().push().getKey();
    }

    private void retriiveData()
    {
        binding.loadingWorks.setVisibility(View.VISIBLE);
        retriiveRef
                .child("Employees")
                .child(id)
                .child("Works")
                .addValueEventListener(new ValueEventListener()
                {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {
                        binding.loadingWorks.setVisibility(View.GONE);
                        for (DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            WorkModel model = dataSnapshot.getValue(WorkModel.class);
                            workModels.add(model);
                        }
                        workAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error)
                    {
                        binding.loadingWorks.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void clickedViews()
    {
        binding.fabPhoneNumber.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
            }
        });

        binding.btnBookNow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                RequestModel requestModel = new RequestModel(randomKey, firebaseAuth.getUid(), image, firstName, lastName, phoneNumber, email, job);

                requestRef
                        .child("Users")
                        .child(firebaseAuth.getUid())
                        .child("Requests")
                        .child(randomKey)
                        .setValue(requestModel)
                        .addOnSuccessListener(new OnSuccessListener<Void>()
                        {
                            @Override
                            public void onSuccess(Void unused)
                            {
                                Toast.makeText(getActivity(), "Done Request is Sucessfully", Toast.LENGTH_SHORT).show();
                                navController.navigate(R.id.action_employeeDetailFragment_to_homeFragment);
                            }
                        }).addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        binding.fabMessage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Navigation.findNavController(view).navigate(R.id.messageFragment);
//                navController.navigate(R.id.action_employeeDetailFragment_to_messageFragment);
            }
        });
    }
}