package com.uptown.gym.trainee.view.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


import com.uptown.gym.trainee.R;
import com.uptown.gym.trainee.databinding.ActivityFitnessTestDetailsBinding;
import com.uptown.gym.trainee.model.fitnesstest.FitnessTest;
import com.uptown.gym.trainee.util.Constants;
import com.uptown.gym.trainee.util.Utils;
import com.uptown.gym.trainee.view.base.BaseActivity;

import androidx.databinding.DataBindingUtil;

public class FitnessTestDetailsActivity extends BaseActivity {

    private ActivityFitnessTestDetailsBinding dataBinding;
    private FitnessTest fitnessTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_fitness_test_details);
        init();
    }

    //*** UI Setup

    private void init() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.Trainee.FITNESS_TEST)) {
            fitnessTest = intent.getParcelableExtra(Constants.Trainee.FITNESS_TEST);
            setupActionBar(Utils.convertLongDateToString(fitnessTest.getCreationTime()));
            displayFitnessTestData(fitnessTest);
        }
    }

    private void displayFitnessTestData(FitnessTest fitnessTest) {
        dataBinding.cardiacEfficiencyScoreEditText.getEditText().setText(String.valueOf(fitnessTest.getCardiacEfficiency()));
        dataBinding.coreStabilityScoreEditText.getEditText().setText(String.valueOf(fitnessTest.getCoreStability()));
        dataBinding.flexibilityScoreEditText.getEditText().setText(String.valueOf(fitnessTest.getFlexibility()));
        dataBinding.strengthScoreEditText.getEditText().setText(String.valueOf(fitnessTest.getStrength()));
        dataBinding.medicalHistoryScoreEditText.getEditText().setText(String.valueOf(fitnessTest.getMedicalHistory()));
        dataBinding.fitnessTestCommentEditText.getEditText().setText(String.valueOf(fitnessTest.getComment()));
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