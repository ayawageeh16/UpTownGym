package com.uptown.gym.trainee.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.uptown.gym.trainee.R;
import com.uptown.gym.trainee.databinding.ActivityHomeBinding;
import com.uptown.gym.trainee.util.Constants;
import com.uptown.gym.trainee.view.ongoingworkoutplans.OnGoingWorkoutPlansFragment;
import com.uptown.gym.trainee.view.profile.ProfileActivity;
import com.uptown.gym.trainee.view.registeration.LoginActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityHomeBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        setSupportActionBar(dataBinding.toolbar);

        dataBinding.navView.setNavigationItemSelectedListener(this);
        setUpDrawerLayout();

        if (savedInstanceState == null) {
            changeFragment(new OnGoingWorkoutPlansFragment(), getString(R.string.ongoing_workout_plans));
            dataBinding.navView.setCheckedItem(R.id.ongoing_menu);
        }

        setUserName();
    }

    @Override
    protected void onResume() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        dataBinding.navView.setCheckedItem(R.id.ongoing_menu);

        super.onResume();
    }

    private void setUserName() {
        SharedPreferences settings = getSharedPreferences(Constants.User.USER_DATA, MODE_PRIVATE);
        if (settings.contains(Constants.User.USER_NAME)) {
            TextView username = dataBinding.navView.getHeaderView(0).findViewById(R.id.username_text_view);
            username.setText(settings.getString(Constants.User.USER_NAME, ""));
        }
    }

    @Override
    public void onBackPressed() {
        if (dataBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            dataBinding.drawerLayout.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            int index = getSupportFragmentManager().getBackStackEntryCount() - 1;
            if (index > -1) {
                FragmentManager.BackStackEntry backEntry = getSupportFragmentManager().getBackStackEntryAt(index);
                String tag = backEntry.getName();
                if (dataBinding.navView.getCheckedItem().getTitle().equals(tag)) {
                    finish();
                } else {
                    getSupportFragmentManager().popBackStack();   // this will display last visible fragment
                }
            } else {
                getSupportFragmentManager().popBackStack();   // this will display last visible fragment
            }
        } else {
            super.onBackPressed();
            finish();
        }
    }

    private void setUpDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dataBinding.drawerLayout, dataBinding.toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dataBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.ongoing_menu: {
                changeFragment(new OnGoingWorkoutPlansFragment(), getString(R.string.ongoing_workout_plans));
                dataBinding.navView.setCheckedItem(R.id.ongoing_menu);
            }
            break;
            case R.id.profile_menu: {
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                dataBinding.navView.setCheckedItem(R.id.profile_menu);
            }
            break;
            case R.id.nav_sign_out: {
                clearSharedPreferences();
                Intent goToLoginActivity = new Intent(this, LoginActivity.class);
                startActivity(goToLoginActivity);
                finish();
            }
            break;
        }
        dataBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    private void changeFragment(Fragment fragment, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        Fragment current = getSupportFragmentManager().findFragmentByTag(tag);
        if (current == null) {
            current = fragment;
            ft.replace(R.id.fragment_container, current, tag);
            ft.addToBackStack(tag).commit();
        } else {
            ft.replace(R.id.fragment_container, fragment, tag);
            ft.addToBackStack(tag).commit();
        }
    }

    private void clearSharedPreferences() {
        SharedPreferences settings = getSharedPreferences(Constants.User.USER_DATA, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.apply();
    }

}