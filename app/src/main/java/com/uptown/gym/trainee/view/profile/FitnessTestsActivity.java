package com.uptown.gym.trainee.view.profile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.uptown.gym.trainee.R;
import com.uptown.gym.trainee.adapter.trainee.FitnessTestsRecyclerViewAdapter;
import com.uptown.gym.trainee.databinding.ActivityFitnessTestsBinding;
import com.uptown.gym.trainee.listener.TraineeListener;
import com.uptown.gym.trainee.model.fitnesstest.FitnessTest;
import com.uptown.gym.trainee.model.trainee.Trainee;
import com.uptown.gym.trainee.util.Constants;
import com.uptown.gym.trainee.util.NetworkConnection;
import com.uptown.gym.trainee.util.ViewUtils;
import com.uptown.gym.trainee.view.base.BaseActivity;
import com.uptown.gym.trainee.viewmodel.TraineeViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

public class FitnessTestsActivity extends BaseActivity implements TraineeListener.FitnessTestListener {

    private static int ADD_FITNESS_TEST = 900;

    private ActivityFitnessTestsBinding dataBinding;
    private TraineeViewModel viewModel;
    private FitnessTestsRecyclerViewAdapter adapter;
    private long userId;
    private boolean isSwipeRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_fitness_tests);
        init();
    }

    private void init() {
        setupActionBar(getString(R.string.fitnessTests));
        viewModel = new ViewModelProvider(this).get(TraineeViewModel.class);
        setupRecyclerView();

        Intent intent = getIntent();
        if (intent.hasExtra(Constants.Trainee.TRAINEE)) {
            userId = intent.getLongExtra(Constants.Trainee.TRAINEE, 0);
            findAllFitnessTests();
        }

        dataBinding.fitnessTestsFragmentSwipeRefresh.setOnRefreshListener(() -> {
            isSwipeRefresh = true;
            findAllFitnessTests();
        });

    }

    private void setupRecyclerView() {
        adapter = new FitnessTestsRecyclerViewAdapter();
        adapter.setListener(this);
        dataBinding.fitnessTestsRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.fitnessTestsRecyclerview.setAdapter(adapter);
        dataBinding.fitnessTestsRecyclerview.setHasFixedSize(true);
    }

    private void stopSwipeLayout() {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (dataBinding.fitnessTestsFragmentSwipeRefresh.isRefreshing()) {
                isSwipeRefresh = false;
                dataBinding.fitnessTestsFragmentSwipeRefresh.setRefreshing(false);
            }
        }, 1000);
    }


    // *** Setup Requests

    private void findAllFitnessTests() {
        if (NetworkConnection.isConnected(this)) {
            onRequestStart(dataBinding.fitnessTestsProgressBar);
            if (isSwipeRefresh) {
                dataBinding.fitnessTestsProgressBar.setVisibility(View.INVISIBLE);
            }
            viewModel.findALLFitnessTests(userId).observe(this, fitnessTestsResponseMainResponse -> {
                onRequestEnd(dataBinding.fitnessTestsProgressBar);
                stopSwipeLayout();
                if (fitnessTestsResponseMainResponse.getResponse() != null) {
                    if (fitnessTestsResponseMainResponse.getStatusCode() == 200) {
                        adapter.setFitnessTests(fitnessTestsResponseMainResponse.getResponse().getContent());
                        dataBinding.fragmentNoFitnessTestsLayout.setVisibility(View.INVISIBLE);
                        dataBinding.fitnessTestsRecyclerview.setVisibility(View.VISIBLE);
                    }
                } else if (fitnessTestsResponseMainResponse.getStatusCode() == 204) {
                    dataBinding.fragmentNoFitnessTestsLayout.setVisibility(View.VISIBLE);
                    dataBinding.fitnessTestsRecyclerview.setVisibility(View.INVISIBLE);
                } else {
                    ViewUtils.showToast(FitnessTestsActivity.this, fitnessTestsResponseMainResponse.getMessage(), Toast.LENGTH_SHORT);
                }
            });
        } else {
            ViewUtils.showToast(FitnessTestsActivity.this, getString(R.string.offline_message), Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void onFitnessTestClicked(FitnessTest fitnessTest) {
        Intent intent = new Intent(this, FitnessTestDetailsActivity.class);
        intent.putExtra(Constants.Trainee.FITNESS_TEST, fitnessTest);
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