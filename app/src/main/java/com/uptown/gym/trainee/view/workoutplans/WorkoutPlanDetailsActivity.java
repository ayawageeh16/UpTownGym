package com.uptown.gym.trainee.view.workoutplans;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.uptown.gym.trainee.R;
import com.uptown.gym.trainee.adapter.workoutplan.WorkoutPlansRecyclerViewAdapter;
import com.uptown.gym.trainee.adapter.workoutplan.WorkoutPlanWeeksRecyclerViewAdapter;
import com.uptown.gym.trainee.databinding.ActivityWorkoutPlanDetailsBinding;
import com.uptown.gym.trainee.listener.WorkoutPlansListener;
import com.uptown.gym.trainee.model.base.MainResponse;
import com.uptown.gym.trainee.model.workout.Workout;
import com.uptown.gym.trainee.model.workoutplan.WorkoutPlan;
import com.uptown.gym.trainee.model.workoutplan.WorkoutPlanResponse;
import com.uptown.gym.trainee.util.NetworkConnection;
import com.uptown.gym.trainee.util.ViewUtils;
import com.uptown.gym.trainee.view.base.BaseActivity;
import com.uptown.gym.trainee.viewmodel.WorkoutPlanViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

public class WorkoutPlanDetailsActivity extends BaseActivity implements WorkoutPlansListener.WorkoutPlanWeekListener {

    private WorkoutPlanWeeksRecyclerViewAdapter adapter;
    private WorkoutPlanViewModel workoutPlanViewModel;
    private WorkoutPlanResponse currentWorkoutPlan;
    private boolean isSwipeRefresh;

    private ActivityWorkoutPlanDetailsBinding dataBinding;
    private Workout workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_workout_plan_details);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        findWorkoutPlanRequest();
    }

    //*** UI Setup

    private void init() {
        getExtras();
        setupActionBar(currentWorkoutPlan.getName());
        setupRecyclerView();

        workoutPlanViewModel = new ViewModelProvider(this).get(WorkoutPlanViewModel.class);
        dataBinding.workoutPlanDetails.workoutPlanWeeksSwipeRefresh.setOnRefreshListener(() -> {
            isSwipeRefresh = true;
            findWorkoutPlanRequest();
        });
    }

    private void getExtras() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra(WorkoutPlansRecyclerViewAdapter.WORKOUT_PLAN_EXTRA)) {
                currentWorkoutPlan = intent.getParcelableExtra(WorkoutPlansRecyclerViewAdapter.WORKOUT_PLAN_EXTRA);
                setHeaderImage();
            }
        }
    }

    private void setHeaderImage() {
        switch (currentWorkoutPlan.getCategory()) {
            case "Build Muscle":
                dataBinding.workoutPlanImg.setImageResource(R.drawable.build_muscle);
                break;
            case "Gain Strength":
                dataBinding.workoutPlanImg.setImageResource(R.drawable.gain_strength);
                break;
            case "Get Fit":
                dataBinding.workoutPlanImg.setImageResource(R.drawable.get_fit);
                break;
            case "Weight Loss":
                dataBinding.workoutPlanImg.setImageResource(R.drawable.weight_oss);
                break;
            default:
                dataBinding.workoutPlanImg.setImageResource(R.drawable.performance);
                break;
        }
    }

    private void setupRecyclerView() {
        adapter = new WorkoutPlanWeeksRecyclerViewAdapter(this);
        adapter.setListener(this);
        dataBinding.workoutPlanDetails.workoutPlanWeeksRecyclerview.setAdapter(adapter);
        dataBinding.workoutPlanDetails.workoutPlanWeeksRecyclerview.setLayoutManager(new GridLayoutManager(this, 3));
        dataBinding.workoutPlanDetails.workoutPlanWeeksRecyclerview.setHasFixedSize(true);
    }

    private void stopSwipeLayout() {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (dataBinding.workoutPlanDetails.workoutPlanWeeksSwipeRefresh.isRefreshing()) {
                isSwipeRefresh = false;
                dataBinding.workoutPlanDetails.workoutPlanWeeksSwipeRefresh.setRefreshing(false);
            }
        }, 1000);
    }


    //*** Requests Setup

    private void findWorkoutPlanRequest() {
        if (NetworkConnection.isConnected(this)) {
            if (currentWorkoutPlan != null) {
                if (!isSwipeRefresh) {
                    dataBinding.workoutPlanDetails.workoutPlanWeeksProgressBar.setVisibility(View.VISIBLE);
                }
                workoutPlanViewModel.findWorkoutPlanById(currentWorkoutPlan.getId()).observe(this, new Observer<MainResponse<WorkoutPlanResponse>>() {
                    @Override
                    public void onChanged(MainResponse<WorkoutPlanResponse> workoutPlanResponseMainResponse) {
                        dataBinding.workoutPlanDetails.workoutPlanWeeksProgressBar.setVisibility(View.GONE);
                        stopSwipeLayout();
                        if (workoutPlanResponseMainResponse.getResponse() != null) {
                            if (workoutPlanResponseMainResponse.getStatusCode() == 200) {
                                dataBinding.workoutPlanDetails.workoutPlanWeeksRecyclerview.setVisibility(View.VISIBLE);
                                dataBinding.setWorkoutPlan(workoutPlanResponseMainResponse.getResponse());
                                adapter.setWorkouts(workoutPlanResponseMainResponse.getResponse().getWorkouts());
                            } else {
                                ViewUtils.showToast(WorkoutPlanDetailsActivity.this, workoutPlanResponseMainResponse.getMessage(), Toast.LENGTH_SHORT);
                            }
                        } else if (workoutPlanResponseMainResponse.getStatusCode() == 204) {
                            dataBinding.workoutPlanDetails.workoutPlanWeeksRecyclerview.setVisibility(View.INVISIBLE);
                        } else {
                            ViewUtils.showToast(WorkoutPlanDetailsActivity.this, workoutPlanResponseMainResponse.getMessage(), Toast.LENGTH_SHORT);
                        }
                    }
                });
            }
        } else {
            ViewUtils.showToast(this, getString(R.string.no_connection), Toast.LENGTH_SHORT);
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

    @Override
    public void onWeekCardClicked(WorkoutPlan workoutPlan) {
//        Intent intent = new Intent(this, WorkoutPlanWeekDaysActivity.class);
//        intent.putExtra(WORKOUT_PLAN_WEEK, workoutPlan);
//        intent.putParcelableArrayListExtra(WORKOUT_PLAN_WEEK_LIST, new ArrayList<>(workoutPlan.getWorkouts()));
//        startActivity(intent);
    }
}
