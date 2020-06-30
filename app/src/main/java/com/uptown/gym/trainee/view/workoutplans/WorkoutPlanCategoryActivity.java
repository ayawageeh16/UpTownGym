package com.uptown.gym.trainee.view.workoutplans;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.uptown.gym.trainee.R;
import com.uptown.gym.trainee.adapter.workoutplan.CategoryWorkoutPlanRecyclerViewAdapter;
import com.uptown.gym.trainee.databinding.ActivityWorkoutPlanCategoryBinding;
import com.uptown.gym.trainee.util.Constants;
import com.uptown.gym.trainee.util.NetworkConnection;
import com.uptown.gym.trainee.util.ViewUtils;
import com.uptown.gym.trainee.view.base.BaseActivity;
import com.uptown.gym.trainee.viewmodel.WorkoutPlanViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

public class WorkoutPlanCategoryActivity extends BaseActivity {

    private ActivityWorkoutPlanCategoryBinding dataBinding;

    private CategoryWorkoutPlanRecyclerViewAdapter adapter;
    private WorkoutPlanViewModel workoutPlanViewModel;
    private boolean isSwipeRefresh;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_workout_plan_category);

        init();
        getExtras();
    }

    @Override
    public void onResume() {
        findAllWorkoutPlansByCategoryRequest();
        super.onResume();
    }

    //*** UI Setup

    private void init() {
        workoutPlanViewModel = new ViewModelProvider(this).get(WorkoutPlanViewModel.class);
        setUpRecyclerView();

        dataBinding.workoutPlansFragmentSwipeRefresh.setOnRefreshListener(() -> {
            isSwipeRefresh = true;
            findAllWorkoutPlansByCategoryRequest();
        });
    }

    private void getExtras() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.WorkoutPlan.CATEGORY)) {
            category = intent.getStringExtra(Constants.WorkoutPlan.CATEGORY);
            setupActionBar(category);
            findAllWorkoutPlansByCategoryRequest();
        }

    }

    private void stopSwipeLayout() {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (dataBinding.workoutPlansFragmentSwipeRefresh.isRefreshing()) {
                isSwipeRefresh = false;
                dataBinding.workoutPlansFragmentSwipeRefresh.setRefreshing(false);
            }
        }, 1000);
    }

    private void setUpRecyclerView() {
        adapter = new CategoryWorkoutPlanRecyclerViewAdapter(this);
        dataBinding.workoutPlansRecyclerView.setHasFixedSize(true);
        dataBinding.workoutPlansRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.workoutPlansRecyclerView.setAdapter(adapter);

//        boolean isPhone = getResources().getBoolean(R.bool.is_phone);
//
//        if (isPhone) {
//            dataBinding.workoutPlansRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        } else {
//            if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
//                dataBinding.workoutPlansRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//            } else {
//                dataBinding.workoutPlansRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
//            }
//        }
    }

    //*** Requests Setup

    private void findAllWorkoutPlansByCategoryRequest() {
        if (NetworkConnection.isConnected(this)) {
            if (!isSwipeRefresh) {
                dataBinding.fragmentWorkoutPlansProgressBar.setVisibility(View.VISIBLE);
            }
            workoutPlanViewModel.findAllWorkoutPlansByCategory(category).observe(this, workoutPlansResponseMainResponse -> {
                dataBinding.fragmentWorkoutPlansProgressBar.setVisibility(View.INVISIBLE);
                stopSwipeLayout();
                if (workoutPlansResponseMainResponse != null) {
                    if (workoutPlansResponseMainResponse.getStatusCode() == 200) {
                        dataBinding.workoutPlansRecyclerView.setVisibility(View.VISIBLE);
                        dataBinding.fragmentNoWorkoutPlansLayout.setVisibility(View.INVISIBLE);
                        adapter.setWorkoutPlanResponses(workoutPlansResponseMainResponse.getResponse().get(0).getWorkoutPlans().getContent());
                    } else if (workoutPlansResponseMainResponse.getStatusCode() == 204) {
                        dataBinding.fragmentNoWorkoutPlansLayout.setVisibility(View.VISIBLE);
                        dataBinding.workoutPlansRecyclerView.setVisibility(View.INVISIBLE);
                    }
                } else {
                    ViewUtils.showToast(this, workoutPlansResponseMainResponse.getMessage(), Toast.LENGTH_LONG);
                }
            });
        } else {
            ViewUtils.showToast(this, getString(R.string.offline_message), Toast.LENGTH_SHORT);
        }
    }

    //*** Actions Setup


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}