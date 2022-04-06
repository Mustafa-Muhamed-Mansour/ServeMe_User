package com.serveme_user.home;

import android.annotation.SuppressLint;
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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.serveme_user.R;
import com.serveme_user.adapter.ServiceAdapter;
import com.serveme_user.adapter.SliderImageAdapter;
import com.serveme_user.adapter.WorkAdapter;
import com.serveme_user.databinding.FragmentHomeBinding;
import com.serveme_user.model.EmployeeModel;
import com.serveme_user.model.ServiceModel;
import com.serveme_user.model.SliderModel;
import com.serveme_user.model.WorkModel;
import java.util.ArrayList;

public class HomeFragment extends Fragment
{

    private NavController navController;
    private FragmentHomeBinding binding;

    private ArrayList<SliderModel> sliderModels;
    private SliderImageAdapter sliderImageAdapter;

//    private ArrayList<ServiceModel> serviceModels;
//    private ServiceAdapter serviceAdapter;

    private ArrayList<WorkModel> workModels;
    private WorkAdapter workAdapter;

    private DatabaseReference retriveRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        sliderModels = new ArrayList<>();
        sliderModels.add(new SliderModel(R.drawable.ic_plumber, "Plumber"));
        sliderModels.add(new SliderModel(R.drawable.ic_carpenter, "Carpenter"));
        sliderModels.add(new SliderModel(R.drawable.ic_air_conditioning, "Air Comditioning"));
        sliderModels.add(new SliderModel(R.drawable.ic_antenna, "Antenna"));
        sliderModels.add(new SliderModel(R.drawable.ic_appliances_home, "Appliances Home"));
        sliderModels.add(new SliderModel(R.drawable.ic_blacksmith, "BlackSmith"));
        sliderModels.add(new SliderModel(R.drawable.ic_computer_services, "Computer Services"));
        sliderModels.add(new SliderModel(R.drawable.ic_driver, "Driver"));
        sliderModels.add(new SliderModel(R.drawable.ic_electrician, "Electrician"));
        sliderModels.add(new SliderModel(R.drawable.ic_macogee, "Macogee"));
        sliderModels.add(new SliderModel(R.drawable.ic_pest_control, "Pest Control"));
        sliderModels.add(new SliderModel(R.drawable.ic_photographer, "Photographer"));

        sliderModels.add(new SliderModel(R.drawable.ic_photographer, "Photographer"));
        sliderModels.add(new SliderModel(R.drawable.ic_photographer, "Photographer"));
        sliderModels.add(new SliderModel(R.drawable.ic_photographer, "Photographer"));
        sliderModels.add(new SliderModel(R.drawable.ic_photographer, "Photographer"));
        sliderModels.add(new SliderModel(R.drawable.ic_photographer, "Photographer"));
        sliderModels.add(new SliderModel(R.drawable.ic_photographer, "Photographer"));
        sliderModels.add(new SliderModel(R.drawable.ic_photographer, "Photographer"));
        sliderModels.add(new SliderModel(R.drawable.ic_photographer, "Photographer"));
        sliderModels.add(new SliderModel(R.drawable.ic_photographer, "Photographer"));
        sliderModels.add(new SliderModel(R.drawable.ic_photographer, "Photographer"));
        sliderModels.add(new SliderModel(R.drawable.ic_photographer, "Photographer"));
        sliderModels.add(new SliderModel(R.drawable.ic_photographer, "Photographer"));
        sliderModels.add(new SliderModel(R.drawable.ic_photographer, "Photographer"));
        sliderModels.add(new SliderModel(R.drawable.ic_photographer, "Photographer"));
        sliderModels.add(new SliderModel(R.drawable.ic_photographer, "Photographer"));
        sliderImageAdapter = new SliderImageAdapter(sliderModels);
        binding.sliderImg.setSliderAdapter(sliderImageAdapter);

        retriveRef = FirebaseDatabase.getInstance().getReference();

//        serviceModels = new ArrayList<>();
//        serviceAdapter = new ServiceAdapter(serviceModels);
//        binding.rVServices.setAdapter(serviceAdapter);
//        binding.rVServices.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
//        binding.rVServices.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
//        binding.rVServices.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));
//        retriveRef
//                .child("Job")
//                .addValueEventListener(new ValueEventListener()
//                {
//                    @SuppressLint("NotifyDataSetChanged")
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot)
//                    {
//
//                        for (DataSnapshot dataSnapshot : snapshot.getChildren())
//                        {
////                            serviceModels.clear();
//                            ServiceModel model = dataSnapshot.getValue(ServiceModel.class);
//                            serviceModels.add(model);
//                        }
//                        serviceAdapter.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error)
//                    {
//                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });

        binding.txtAllServices.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                navController.navigate(R.id.action_homeFragment_to_allServicesFragment);
            }
        });

        binding.linearCarpenter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                HomeFragmentDirections.ActionHomeFragmentToEmployeeHomeFragment action = HomeFragmentDirections.actionHomeFragmentToEmployeeHomeFragment();
                action.setJob("Carpenter");
                navController.navigate(action);
            }
        });

        binding.linearPlumber.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                HomeFragmentDirections.ActionHomeFragmentToEmployeeHomeFragment action = HomeFragmentDirections.actionHomeFragmentToEmployeeHomeFragment();
                action.setJob("Plumber");
                navController.navigate(action);
            }
        });

        binding.imgMessage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                navController.navigate(R.id.action_homeFragment_to_messageFragment);
            }
        });

        workModels = new ArrayList<>();
        workAdapter = new WorkAdapter(workModels);
        binding.rVWorks.setAdapter(workAdapter);
        binding.rVWorks.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        binding.rVWorks.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));

        binding.loadingAllWorks.setVisibility(View.VISIBLE);
        retriveRef
                .child("Works")
                .addValueEventListener(new ValueEventListener()
                {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {
                        binding.loadingAllWorks.setVisibility(View.GONE);
                        for (DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
//                            workModels.clear();
                            WorkModel model = dataSnapshot.getValue(WorkModel.class);
                            workModels.add(model);
                        }
                        workAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error)
                    {
                        binding.loadingAllWorks.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

//    app-ID     ca-app-pub-7760695200810337~4005873206
//    unit-ID    ca-app-pub-7760695200810337/1188138175
//        binding.adsView.setAdSize(AdSize.BANNER);
//        binding.adsView.setAdUnitId("ca-app-pub-7760695200810337/1188138175");
//        binding.adsView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
//        MobileAds.initialize(getContext(), new OnInitializationCompleteListener()
//        {
//            @Override
//            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus)
//            {
//            }
//        });
        AdRequest adRequest = new AdRequest.Builder().build();
        binding.adsView.loadAd(adRequest);

    }
}