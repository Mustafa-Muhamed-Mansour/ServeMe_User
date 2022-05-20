package com.serveme_user.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.serveme_user.R;
import com.serveme_user.databinding.FragmentProfileBinding;
import com.serveme_user.model.UserModel;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment
{

    private NavController navController;
    private FragmentProfileBinding binding;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference retriveRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);



        initViews(view);
        initDatabase();
        retriveData();
        clickedViews();


    }

    private void initViews(View view)
    {
        navController = Navigation.findNavController(view);
    }

    private void initDatabase()
    {
        firebaseAuth = FirebaseAuth.getInstance();
        retriveRef = FirebaseDatabase.getInstance().getReference();
    }

    private void retriveData()
    {
        retriveRef
                .child("Users")
                .child(firebaseAuth.getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DataSnapshot>()
                {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot)
                    {

                        UserModel userModel = dataSnapshot.getValue(UserModel.class);

                        if (userModel.getImage() == null)
                        {
                            Picasso
                                    .get()
                                    .load(userModel.getImage())
                                    .placeholder(R.drawable.ic_no_photo)
                                    .into(binding.imgProfile);
                        }

                        else
                        {
                            Picasso
                                    .get()
                                    .load(userModel.getImage())
                                    .into(binding.imgProfile);
                        }

                        if (userModel.getGender().equals("Male"))
                        {
                            binding.editGenderProfile.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_symbol_male, 0, 0, 0);
                            binding.editGenderProfile.setCompoundDrawablePadding(10);
                            binding.editGenderProfile.setText(userModel.getGender());
                        }
                        if (userModel.getGender().equals("Female"))
                        {
                            binding.editGenderProfile.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_symbol_woman, 0, 0, 0);
                            binding.editGenderProfile.setCompoundDrawablePadding(10);
                            binding.editGenderProfile.setText(userModel.getGender());
                        }

                        binding.editFnProfile.setText(userModel.getFirstName());
                        binding.editLnProfile.setText(userModel.getLastName());
                        binding.editPhoneProfile.setText(userModel.getPhoneNumber());
                        binding.editEmailProfile.setText(userModel.getEmail());
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

    private void clickedViews()
    {
        binding.btnSaveProfile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Navigation.findNavController(view).navigate(R.id.homeFragment);
//                navController.navigate(R.id.action_profileFragment_to_homeFragment);
            }
        });

        binding.btnExitProfile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                firebaseAuth.signOut();
//                navController.navigate(R.id.action_profileFragment_to_loginFragment);
                Navigation.findNavController(view).navigate(R.id.loginFragment);
            }
        });
    }
}