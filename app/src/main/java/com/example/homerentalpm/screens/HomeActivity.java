package com.example.homerentalpm.screens;

import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.example.homerentalpm.R;
import com.example.homerentalpm.fragments.ChatFragment;
import com.example.homerentalpm.fragments.FavouriteFragment;
import com.example.homerentalpm.fragments.HomeFragment;
import com.example.homerentalpm.fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import org.jetbrains.annotations.NotNull;

public class HomeActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this);
        loadFragment(new HomeFragment());

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

        Fragment fragment = null;
        int itemId = item.getItemId();
        if (itemId == R.id.menu_home) {
            fragment = new HomeFragment();
        } else if (itemId == R.id.menu_fav) {
            fragment = new FavouriteFragment();
        } else if (itemId == R.id.menu_chat) {
            fragment = new ChatFragment();
        } else if (itemId == R.id.menu_setting) {
            fragment = new SettingsFragment();
        }

        return loadFragment(fragment);
    }

    boolean loadFragment(Fragment fragment){
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        }
        return false;
    }

}

