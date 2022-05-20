package com.serveme_user.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.serveme_user.R;
import com.serveme_user.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{

    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initViews(MainActivity.this);

    }

    private void initViews(MainActivity mainActivity)
    {
        navController = Navigation.findNavController(mainActivity, R.id.nav_host);
        NavigationUI.setupWithNavController(binding.bottomNavView, navController);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener()
        {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments)
            {
                switch (destination.getId())
                {
                    case R.id.splashFragment:
                    case R.id.loginFragment:
                    case R.id.registerFragment:
                    case R.id.messageFragment:
                    case R.id.employeeHomeFragment:
                    case R.id.employeeDetailFragment:
                    case R.id.allServicesFragment:
                    case R.id.searchFragment:
//                    case R.id.chatFragment:
                        binding.bottomNavView.setVisibility(View.GONE);
                        break;
                    default:
                        binding.bottomNavView.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
    }
}