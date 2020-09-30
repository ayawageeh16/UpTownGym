package com.uptown.gym.trainee.view.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.uptown.gym.trainee.R;
import com.uptown.gym.trainee.adapter.trainee.InBodiesRecyclerViewAdapter;
import com.uptown.gym.trainee.databinding.ActivityInBodiesBinding;
import com.uptown.gym.trainee.listener.TraineeListener;
import com.uptown.gym.trainee.model.inbody.InBody;
import com.uptown.gym.trainee.util.Constants;
import com.uptown.gym.trainee.util.NetworkConnection;
import com.uptown.gym.trainee.util.ViewUtils;
import com.uptown.gym.trainee.view.base.BaseActivity;
import com.uptown.gym.trainee.viewmodel.TraineeViewModel;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

public class InBodiesActivity extends BaseActivity implements TraineeListener.InBodiesListener {

    private static int ADD_INBODY = 950;

    private ActivityInBodiesBinding dataBinding;
    private TraineeViewModel traineeViewModel;
    private long userId;
    private InBodiesRecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_in_bodies);
        dataBinding.setListener(this);
        init();
    }

    private void init() {
        setupActionBar(getString(R.string.inbodies));
        traineeViewModel = new ViewModelProvider(this).get(TraineeViewModel.class);
        setupRecyclerView();

        Intent intent = getIntent();
        if (intent.hasExtra(Constants.Trainee.TRAINEE)) {
            userId = intent.getLongExtra(Constants.Trainee.TRAINEE, 0);
            findAllInBodies();
        }

    }

    private void setupRecyclerView() {
        adapter = new InBodiesRecyclerViewAdapter();
        adapter.setListener(this);
        dataBinding.inbodiesRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.inbodiesRecyclerview.setHasFixedSize(true);
        dataBinding.inbodiesRecyclerview.setAdapter(adapter);
    }

    // *** Setup Requests

    private void findAllInBodies() {
        if (NetworkConnection.isConnected(this)) {
            traineeViewModel.findAllInbodies(userId).observe(this, inBodiesMainResponse -> {
                if (inBodiesMainResponse.getResponse() != null) {
                    if (inBodiesMainResponse.getStatusCode() == 200) {
                        adapter.setInBodies(inBodiesMainResponse.getResponse().getInBodyModels());
                        dataBinding.fragmentNoInbodiesLayout.setVisibility(View.INVISIBLE);
                        dataBinding.inbodiesRecyclerview.setVisibility(View.VISIBLE);
                    }
                } else if (inBodiesMainResponse.getStatusCode() == 204) {
                    dataBinding.fragmentNoInbodiesLayout.setVisibility(View.VISIBLE);
                    dataBinding.inbodiesRecyclerview.setVisibility(View.INVISIBLE);
                } else {
                    ViewUtils.showToast(InBodiesActivity.this, inBodiesMainResponse.getMessage(), Toast.LENGTH_SHORT);
                }
            });
        } else {
            ViewUtils.showToast(this, getString(R.string.offline_message), Toast.LENGTH_SHORT);
        }
    }

    // *** Setup Actions

    @Override
    public void onInBodyClicked(InBody inBody) {
        Intent intent = new Intent(this, InBodyDetailsActivity.class);
        intent.putExtra(Constants.Trainee.INBODY, inBody);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_INBODY && resultCode == RESULT_OK) {
            findAllInBodies();
        }
    }
}