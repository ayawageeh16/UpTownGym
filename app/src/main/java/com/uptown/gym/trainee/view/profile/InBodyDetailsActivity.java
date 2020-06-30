package com.uptown.gym.trainee.view.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;


import com.uptown.gym.trainee.R;
import com.uptown.gym.trainee.databinding.ActivityInBodyDetailsBinding;
import com.uptown.gym.trainee.model.inbody.InBody;
import com.uptown.gym.trainee.util.Constants;
import com.uptown.gym.trainee.util.Utils;
import com.uptown.gym.trainee.view.base.BaseActivity;

import java.util.Objects;

import androidx.databinding.DataBindingUtil;

public class InBodyDetailsActivity extends BaseActivity {

    private ActivityInBodyDetailsBinding dataBinding;
    private InBody inBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_in_body_details);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.Trainee.INBODY)) {
            inBody = intent.getParcelableExtra(Constants.Trainee.INBODY);
            setupActionBar(Utils.convertLongDateToString(inBody.getCreationTime()));
            displayInBodyData(inBody);
        }
    }

    private void displayInBodyData(InBody inBodyModel) {
        Objects.requireNonNull(dataBinding.clientFatControlTextInput.getEditText()).setText(String.valueOf(inBodyModel.getFatControl()));
        Objects.requireNonNull(dataBinding.clientBodyScoreTextInput.getEditText()).setText(String.valueOf(inBodyModel.getBodyScore()));
        Objects.requireNonNull(dataBinding.clientBasalMetabolicRateTextInput.getEditText()).setText(String.valueOf(inBodyModel.getMetabolicRate()));
        Objects.requireNonNull(dataBinding.clientFatPercentageTextInput.getEditText()).setText(String.valueOf(inBodyModel.getFatPercentage()));
        Objects.requireNonNull(dataBinding.clientFatWeightTextInput.getEditText()).setText(String.valueOf(inBodyModel.getFatWeight()));
        Objects.requireNonNull(dataBinding.clientHeightTextInput.getEditText()).setText(String.valueOf(inBodyModel.getHeight()));
        Objects.requireNonNull(dataBinding.clientMuscleMassTextInput.getEditText()).setText(String.valueOf(inBodyModel.getMuscleMass()));
        Objects.requireNonNull(dataBinding.clientMuscleControlTextInput.getEditText()).setText(String.valueOf(inBodyModel.getMuscleControl()));
        Objects.requireNonNull(dataBinding.clientObesityDegreeTextInput.getEditText()).setText(String.valueOf(inBodyModel.getObesityDegree()));
        Objects.requireNonNull(dataBinding.clientTargetWeightTextInput.getEditText()).setText(String.valueOf(inBodyModel.getTargetWeight()));
        Objects.requireNonNull(dataBinding.clientTotalWeightTextInput.getEditText()).setText(String.valueOf(inBodyModel.getWeight()));
        Objects.requireNonNull(dataBinding.clientVisceralFatLevelTextInput.getEditText()).setText(String.valueOf(inBodyModel.getFatLevel()));
        Objects.requireNonNull(dataBinding.clientWaistHipRatioTextInput.getEditText()).setText(String.valueOf(inBodyModel.getWaistHipRatio()));
        Objects.requireNonNull(dataBinding.clientWeightControlTextInput.getEditText()).setText(String.valueOf(inBodyModel.getWeightControl()));
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