package com.serveme_user.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.serveme_user.R;
import com.serveme_user.databinding.FragmentSplashBinding;

public class SplashFragment extends Fragment
{

    private NavController navController;
    private FragmentSplashBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentSplashBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);


        initView(view);
        initPostDelay(view);

    }

    private void initView(View view)
    {
        navController = Navigation.findNavController(view);
    }

    private void initPostDelay(View view)
    {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
//                navController.navigate(R.id.action_splashFragment_to_loginFragment);
                Navigation.findNavController(view).navigate(R.id.loginFragment);
            }
        }, 3500);
    }
}