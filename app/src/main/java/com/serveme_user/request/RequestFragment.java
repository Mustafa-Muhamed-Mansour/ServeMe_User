package com.serveme_user.request;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.serveme_user.R;
import com.serveme_user.adapter.RequestAdapter;
import com.serveme_user.databinding.BottomSheetUserBinding;
import com.serveme_user.databinding.FragmentRequestBinding;
import com.serveme_user.model.EmployeeModel;
import com.serveme_user.model.RequestModel;
import com.serveme_user.model.UserModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class RequestFragment extends Fragment
{

    private NavController navController;
    private FragmentRequestBinding binding;

    private ArrayList<RequestModel> requestModels;
    private RequestAdapter requestAdapter;

    private UserModel userModel;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference retriveRef;

    private BottomSheetDialog bottomSheet;
    private BottomSheetUserBinding bottomSheetUserBinding;

    private SharedPreferences sharedPref;
    public static final String KEY_SHARED_PREF = "myPref";
    private String id, randomKey;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentRequestBinding.inflate(inflater, container, false);
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

        userModel = new UserModel();

        sharedPref = getActivity().getSharedPreferences(KEY_SHARED_PREF, MODE_PRIVATE);
        id = sharedPref.getString("ID_Employee", null);

        requestModels = new ArrayList<>();
        requestAdapter = new RequestAdapter(requestModels);
        binding.rVRequest.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.rVRequest.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        binding.rVRequest.setAdapter(requestAdapter);
    }

    private void initDatabase()
    {
        randomKey = FirebaseDatabase.getInstance().getReference().push().getKey();
        firebaseAuth = FirebaseAuth.getInstance();
        retriveRef = FirebaseDatabase.getInstance().getReference();
    }

    private void retriveData()
    {
        binding.loadingRequest.setVisibility(View.VISIBLE);
        retriveRef
                .child("Users")
                .child(firebaseAuth.getUid())
                .child("Requests")
                .addValueEventListener(new ValueEventListener()
                {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {
                        binding.loadingRequest.setVisibility(View.GONE);

                        for (DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            RequestModel model = dataSnapshot.getValue(RequestModel.class);
                            requestModels.add(model);
                        }
                        requestAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error)
                    {
                        binding.loadingRequest.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void clickedViews()
    {
        binding.btnDone.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                bottomSheet = new BottomSheetDialog(getActivity());
                bottomSheetUserBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.bottom_sheet_user, getActivity().findViewById(R.id.relaive_bottom_sheet), false);
                bottomSheet.setContentView(bottomSheetUserBinding.getRoot());

                retriveRef
                        .child("Users")
                        .child(firebaseAuth.getUid())
                        .addValueEventListener(new ValueEventListener()
                        {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot)
                            {
                                userModel = snapshot.getValue(UserModel.class);
                                Picasso
                                        .get()
                                        .load(userModel.getImage())
                                        .into(bottomSheetUserBinding.imgBottomSheetUser);

                                bottomSheetUserBinding.txtNameBottomSheetUser.setText(userModel.getFirstName() + " " + userModel.getLastName());
                                bottomSheetUserBinding.txtPhoneBottomSheetUser.setText(userModel.getPhoneNumber());
                                bottomSheetUserBinding.txtEmailBottomSheetUser.setText(userModel.getEmail());

                                if (userModel.getGender().equals("Male"))
                                {
                                    bottomSheetUserBinding.txtGenderBottomSheetUser.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_symbol_male, 0, 0, 0);
                                    bottomSheetUserBinding.txtGenderBottomSheetUser.setCompoundDrawablePadding(10);
                                    bottomSheetUserBinding.txtGenderBottomSheetUser.setText(userModel.getGender());
                                }
                                if (userModel.getGender().equals("Female"))
                                {
                                    bottomSheetUserBinding.txtGenderBottomSheetUser.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_symbol_woman, 0, 0, 0);
                                    bottomSheetUserBinding.txtGenderBottomSheetUser.setCompoundDrawablePadding(10);
                                    bottomSheetUserBinding.txtGenderBottomSheetUser.setText(userModel.getGender());
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error)
                            {
                                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                bottomSheetUserBinding.btnOk.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        bottomSheetUserBinding.loadingBottomSheetUser.setVisibility(View.VISIBLE);
                        bottomSheetUserBinding.btnOk.setVisibility(View.GONE);

                        HashMap<String, Object> dataUser = new HashMap<>();
                        dataUser.put("User_Image", userModel.getImage());
                        dataUser.put("User_Name", bottomSheetUserBinding.txtNameBottomSheetUser.getText().toString());
                        dataUser.put("User_Phone", bottomSheetUserBinding.txtPhoneBottomSheetUser.getText().toString());
                        dataUser.put("User_Email", bottomSheetUserBinding.txtEmailBottomSheetUser.getText().toString());
                        dataUser.put("User_Gender", bottomSheetUserBinding.txtGenderBottomSheetUser.getText().toString());
                        dataUser.put("User_ID", userModel.getId());
                        dataUser.put("User_Random_Key", userModel.getRandomKey());

                        retriveRef
                                .child("Employees")
                                .child(id)
                                .child("Request")
                                .child(randomKey)
                                .setValue(dataUser)
                                .addOnSuccessListener(new OnSuccessListener<Void>()
                                {
                                    @Override
                                    public void onSuccess(Void unused)
                                    {
                                        bottomSheetUserBinding.loadingBottomSheetUser.setVisibility(View.GONE);
                                        bottomSheetUserBinding.btnOk.setVisibility(View.GONE);

                                        Toast.makeText(getContext(), "Done Yes", Toast.LENGTH_SHORT).show();
//                                        navController.navigate(R.id.action_requestFragment_to_homeFragment);
                                        Navigation.findNavController(view).navigate(R.id.homeFragment);
                                        retriveRef
                                                .child("Users")
                                                .child(firebaseAuth.getUid())
                                                .child("Requests")
                                                .removeValue();

                                        bottomSheet.cancel();
                                    }
                                }).addOnFailureListener(new OnFailureListener()
                        {
                            @Override
                            public void onFailure(@NonNull Exception e)
                            {
                                bottomSheetUserBinding.loadingBottomSheetUser.setVisibility(View.GONE);
                                bottomSheetUserBinding.btnOk.setVisibility(View.VISIBLE);

                                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
//                        Toast.makeText(getActivity(), "Lol", Toast.LENGTH_SHORT).show();
                    }
                });

                FrameLayout frameLayout = bottomSheet.findViewById(com.google.android.material.R.id.design_bottom_sheet);
                BottomSheetBehavior<View> viewBottomSheetBehavior = BottomSheetBehavior.from(frameLayout);
                viewBottomSheetBehavior.setPeekHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
                viewBottomSheetBehavior.setState(viewBottomSheetBehavior.STATE_EXPANDED);
                bottomSheet.show();
            }
        });
    }
}