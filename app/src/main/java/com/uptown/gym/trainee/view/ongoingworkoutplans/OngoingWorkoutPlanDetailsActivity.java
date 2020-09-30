package com.uptown.gym.trainee.view.ongoingworkoutplans;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.uptown.gym.trainee.R;
import com.uptown.gym.trainee.adapter.workoutplan.WorkoutPlanWeeksRecyclerViewAdapter;
import com.uptown.gym.trainee.databinding.ActivityOngoingWorkoutPlanDetailsBinding;
import com.uptown.gym.trainee.listener.WorkoutPlansListener;
import com.uptown.gym.trainee.model.base.MainResponse;
import com.uptown.gym.trainee.model.ongoingworkoutplan.OnGoingWorkoutPlan;
import com.uptown.gym.trainee.model.workoutplan.WorkoutPlan;
import com.uptown.gym.trainee.util.NetworkConnection;
import com.uptown.gym.trainee.util.ViewUtils;
import com.uptown.gym.trainee.view.base.BaseActivity;
import com.uptown.gym.trainee.view.workoutplans.WorkoutPlanWeekDaysActivity;
import com.uptown.gym.trainee.viewmodel.OnGoingWorkoutPlanViewModel;

import java.util.ArrayList;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import static com.uptown.gym.trainee.util.Constants.OnGoingWorkoutPlan.IS_ONGOING;
import static com.uptown.gym.trainee.util.Constants.OnGoingWorkoutPlan.ONGOING_WORKOUT_PLAN;
import static com.uptown.gym.trainee.util.Constants.OnGoingWorkoutPlan.ON_GOING_WORKOUT_PLAN;
import static com.uptown.gym.trainee.util.Constants.OnGoingWorkoutPlan.WEEK_WORKOUT;
import static com.uptown.gym.trainee.util.Constants.OnGoingWorkoutPlan.WEEK_WORKOUT_DAYS_LIST;

public class OngoingWorkoutPlanDetailsActivity extends BaseActivity implements WorkoutPlansListener.WorkoutPlanWeekListener {

    private ActivityOngoingWorkoutPlanDetailsBinding dataBinding;
    private WorkoutPlanWeeksRecyclerViewAdapter adapter;
    private OnGoingWorkoutPlanViewModel onGoingWorkoutPlanViewModel;
    private OnGoingWorkoutPlan ongoingWorkoutPlan;
    private boolean isSwipeRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_ongoing_workout_plan_details);
        getExtras();
        init();
    }


    //*** UI Setup

    private void getExtras() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(ONGOING_WORKOUT_PLAN)) {
            ongoingWorkoutPlan = intent.getParcelableExtra(ONGOING_WORKOUT_PLAN);
            setHeaderImage();
        }
    }

    private void setHeaderImage() {
        switch (ongoingWorkoutPlan.getWorkoutPlan().getCategory()) {
            case "Build Muscle":
                dataBinding.onGoingImg.setImageResource(R.drawable.build_muscle);
                break;
            case "Gain Strength":
                dataBinding.onGoingImg.setImageResource(R.drawable.gain_strength);
                break;
            case "Get Fit":
                dataBinding.onGoingImg.setImageResource(R.drawable.get_fit);
                break;
            case "Weight Loss":
                dataBinding.onGoingImg.setImageResource(R.drawable.weight_oss);
                break;
            default:
                dataBinding.onGoingImg.setImageResource(R.drawable.performance);
                break;
        }
    }

    private void init() {
        onGoingWorkoutPlanViewModel = new ViewModelProvider(this).get(OnGoingWorkoutPlanViewModel.class);
        if (ongoingWorkoutPlan != null) {
            setupActionBar(ongoingWorkoutPlan.getWorkoutPlan().getName());
            findOnGoingWorkoutPlanById();
        }

        setRecyclerView();
        dataBinding.workoutPlanDetails.workoutPlanWeeksSwipeRefresh.setOnRefreshListener(() -> {
            isSwipeRefresh = true;
            findOnGoingWorkoutPlanById();
        });
    }

    private void setRecyclerView() {
        adapter = new WorkoutPlanWeeksRecyclerViewAdapter(this);
        adapter.setListener(this);
        dataBinding.workoutPlanDetails.workoutPlanWeeksRecyclerview.setLayoutManager(new GridLayoutManager(this, 3));
        dataBinding.workoutPlanDetails.workoutPlanWeeksRecyclerview.setAdapter(adapter);
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

    private void findOnGoingWorkoutPlanById() {
        if (NetworkConnection.isConnected(this)) {
            onRequestStart(dataBinding.workoutPlanDetails.workoutPlanWeeksProgressBar);
            if (!isSwipeRefresh) {
                dataBinding.workoutPlanDetails.workoutPlanWeeksProgressBar.setVisibility(View.VISIBLE);
            }
            onGoingWorkoutPlanViewModel.findOnGoingWorkoutPlanById(ongoingWorkoutPlan.getWorkoutPlan().getId(),
                    ongoingWorkoutPlan.getId()).observe(this, new Observer<MainResponse<OnGoingWorkoutPlan>>() {
                @Override
                public void onChanged(MainResponse<OnGoingWorkoutPlan> onGoingWorkoutPlanMainResponse) {
                    onRequestEnd(dataBinding.workoutPlanDetails.workoutPlanWeeksProgressBar);
                    stopSwipeLayout();
                    if (onGoingWorkoutPlanMainResponse.getResponse() != null) {
                        if (onGoingWorkoutPlanMainResponse.getStatusCode() == 200) {
                            ongoingWorkoutPlan = onGoingWorkoutPlanMainResponse.getResponse();
                            dataBinding.setOnGoingWorkoutPlan(ongoingWorkoutPlan);
                            adapter.setWorkouts(onGoingWorkoutPlanMainResponse.getResponse().getWorkoutPlan().getWorkouts());
                        } else {
                            ViewUtils.showToast(OngoingWorkoutPlanDetailsActivity.this, onGoingWorkoutPlanMainResponse.getMessage(), Toast.LENGTH_SHORT);
                        }
                    }else{
                        ViewUtils.showToast(OngoingWorkoutPlanDetailsActivity.this, onGoingWorkoutPlanMainResponse.getMessage(), Toast.LENGTH_SHORT);
                    }
                }
            });
        } else {
            ViewUtils.showToast(this, getString(R.string.offline_message), Toast.LENGTH_SHORT);
        }
    }

    //*** Actions Setup

    @Override
    public void onWeekCardClicked(WorkoutPlan workoutPlan) {
        Intent intent = new Intent(this, WorkoutPlanWeekDaysActivity.class);
        intent.putExtra(ON_GOING_WORKOUT_PLAN, ongoingWorkoutPlan);
        intent.putExtra(WEEK_WORKOUT, workoutPlan);
        intent.putParcelableArrayListExtra(WEEK_WORKOUT_DAYS_LIST, new ArrayList<>(workoutPlan.getWorkouts()));
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
