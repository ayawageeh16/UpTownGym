package com.uptown.gym.trainee.view.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.uptown.gym.trainee.R;
import com.uptown.gym.trainee.databinding.ActivityProfileBinding;
import com.uptown.gym.trainee.listener.TraineeListener;
import com.uptown.gym.trainee.util.Constants;
import com.uptown.gym.trainee.util.NetworkConnection;
import com.uptown.gym.trainee.util.ViewUtils;
import com.uptown.gym.trainee.view.base.BaseActivity;
import com.uptown.gym.trainee.viewmodel.TraineeViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

public class ProfileActivity extends BaseActivity implements TraineeListener.TraineeProfileListener {

    private ActivityProfileBinding dataBinding;
    private String username;
    private long userId;
    private TraineeViewModel traineeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        dataBinding.setListener(this);
        init();
    }

    private void init() {
        setupActionBar(getString(R.string.profile));
        traineeViewModel = new ViewModelProvider(this).get(TraineeViewModel.class);

        SharedPreferences settings = getSharedPreferences(Constants.User.USER_DATA, MODE_PRIVATE);
        if (settings.contains(Constants.User.USER_NAME)) {
            username = settings.getString(Constants.User.USER_NAME, "");
            userId = settings.getLong(Constants.User.USER_ID, 0);
            findTraineeByIdAPIRequest();
        }
    }

    // *** Setup Request

    private void findTraineeByIdAPIRequest() {
        if (NetworkConnection.isConnected(this)) {
            onRequestStart(dataBinding.traineeProfileProgressBar);
            traineeViewModel.findTraineeById(userId).observe(this, traineeMainResponse -> {
                onRequestEnd(dataBinding.traineeProfileProgressBar);
                if (traineeMainResponse.getStatusCode() == 200) {
                    dataBinding.setTrainee(traineeMainResponse.getResponse());
                    dataBinding.profileTraineeAge.setText(String.valueOf(traineeMainResponse.getResponse().getAge()));

                } else {
                    ViewUtils.showToast(ProfileActivity.this, traineeMainResponse.getMessage(), Toast.LENGTH_LONG);
                }
            });
        } else {
            ViewUtils.showToast(ProfileActivity.this, getString(R.string.offline_message), Toast.LENGTH_LONG);
        }
    }

    @Override
    public void onFitnessTestClicked() {
        Intent intent = new Intent(this, FitnessTestsActivity.class);
        intent.putExtra(Constants.Trainee.TRAINEE, userId);
        startActivity(intent);
    }

    @Override
    public void onInBodyClicked() {
        Intent intent = new Intent(this, InBodiesActivity.class);
        intent.putExtra(Constants.Trainee.TRAINEE, userId);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}