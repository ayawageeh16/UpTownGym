package com.uptown.gym.trainee.view.workoutplans;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.uptown.gym.trainee.R;
import com.uptown.gym.trainee.adapter.workoutplan.WorkoutPlanWeekDaysRecyclerViewAdapter;
import com.uptown.gym.trainee.databinding.ActivityWorkoutPlanWeekDaysBinding;
import com.uptown.gym.trainee.listener.WorkoutPlansListener;
import com.uptown.gym.trainee.model.ongoingworkoutplan.OnGoingWorkoutPlan;
import com.uptown.gym.trainee.model.workoutplan.WorkoutPlan;
import com.uptown.gym.trainee.model.workoutplan.WorkoutPlanResponse;
import com.uptown.gym.trainee.model.workoutplan.Workouts;
import com.uptown.gym.trainee.util.NetworkConnection;
import com.uptown.gym.trainee.util.ViewUtils;
import com.uptown.gym.trainee.view.base.BaseActivity;
import com.uptown.gym.trainee.view.ongoingworkoutplans.OnGoingWorkoutPlanDayAppointmentsActivity;
import com.uptown.gym.trainee.viewmodel.WorkoutPlanViewModel;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import static com.uptown.gym.trainee.util.Constants.OnGoingWorkoutPlan.ON_GOING_WORKOUT_PLAN;
import static com.uptown.gym.trainee.util.Constants.OnGoingWorkoutPlan.WEEK_WORKOUT;
import static com.uptown.gym.trainee.util.Constants.OnGoingWorkoutPlan.WEEK_WORKOUT_DAYS_LIST;
import static com.uptown.gym.trainee.util.Constants.Workout.WORKOUT;
import static com.uptown.gym.trainee.util.Constants.Workout.WORKOUT_PLAN;
import static com.uptown.gym.trainee.util.Constants.Workout.WORKOUT_PLAN_WEEK;
import static com.uptown.gym.trainee.util.Constants.Workout.WORKOUT_PLAN_WEEK_LIST;

public class WorkoutPlanWeekDaysActivity extends BaseActivity implements WorkoutPlansListener.WorkoutPlanWeekDayListener {

    private ActivityWorkoutPlanWeekDaysBinding dataBinding;
    private WorkoutPlanWeekDaysRecyclerViewAdapter adapter;
    private WorkoutPlan currentWorkout;
    private List<Workouts> currentWorkoutPlanDays;
    private WorkoutPlanResponse currentWorkoutPlan;

    // OnGoingWorkoutPlanDetails Intent
    private OnGoingWorkoutPlan onGoingWorkoutPlan;
    private WorkoutPlan workoutPlan;
    private boolean isFromOnGoingWorkoutPlan;
    private WorkoutPlanViewModel workoutPlanViewModel;
    private Workouts planDay;

    private boolean isEdit;
    private boolean isSwipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_workout_plan_week_days);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        findAllWorkoutPlanDays();
    }

    //*** UI Setup

    private void init() {
        workoutPlanViewModel = new ViewModelProvider(this).get(WorkoutPlanViewModel.class);
        Intent intent = getIntent();
        if (intent.hasExtra(WORKOUT_PLAN_WEEK)) {
            currentWorkout = intent.getParcelableExtra(WORKOUT_PLAN_WEEK);
            currentWorkoutPlan = intent.getParcelableExtra(WORKOUT_PLAN);
            currentWorkoutPlanDays = intent.getParcelableArrayListExtra(WORKOUT_PLAN_WEEK_LIST);
            setActionBar(currentWorkout.getWeekNumber());
        } else if (intent.hasExtra(ON_GOING_WORKOUT_PLAN)) {
            onGoingWorkoutPlan = intent.getParcelableExtra(ON_GOING_WORKOUT_PLAN);
            currentWorkoutPlan = onGoingWorkoutPlan.getWorkoutPlan();
            workoutPlan = intent.getParcelableExtra(WEEK_WORKOUT);
            currentWorkout = workoutPlan;
            currentWorkoutPlanDays = intent.getParcelableArrayListExtra(WEEK_WORKOUT_DAYS_LIST);
            setActionBar(workoutPlan.getWeekNumber());
            isFromOnGoingWorkoutPlan = true;
        }
        setUpRecyclerViewAdapter();

        dataBinding.workoutPlansWeekDaysSwipeRefresh.setOnRefreshListener(() -> {
            isSwipeRefresh = true;
            findAllWorkoutPlanDays();
        });
    }

    private void setActionBar(int weekNumber) {
        setupActionBar(getString(R.string.week) + " " + weekNumber);
    }

    private void stopSwipeLayout() {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (dataBinding.workoutPlansWeekDaysSwipeRefresh.isRefreshing()) {
                isSwipeRefresh = false;
                dataBinding.workoutPlansWeekDaysSwipeRefresh.setRefreshing(false);
            }
        }, 1000);
    }

    private void setUpRecyclerViewAdapter() {
        adapter = new WorkoutPlanWeekDaysRecyclerViewAdapter();
        adapter.setListener(this);
        dataBinding.workoutPlansWeekDaysRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.workoutPlansWeekDaysRecyclerView.setHasFixedSize(true);
        dataBinding.workoutPlansWeekDaysRecyclerView.setAdapter(adapter);
    }

    // *** Requests Setup

    private void findAllWorkoutPlanDays() {
        if (NetworkConnection.isConnected(this)) {
            if (!isSwipeRefresh) {
                dataBinding.fragmentWorkoutPlansWeekDaysProgressBar.setVisibility(View.VISIBLE);
            }
            workoutPlanViewModel.findAllWorkouts(currentWorkoutPlan.getId(), currentWorkout.getWeekNumber()).observe(this, listMainResponse -> {
                dataBinding.fragmentWorkoutPlansWeekDaysProgressBar.setVisibility(View.INVISIBLE);
                stopSwipeLayout();
                if(listMainResponse.getResponse() != null){
                    if (listMainResponse.getStatusCode() == 200) {
                        adapter.setWeekWorkoutDays(listMainResponse.getResponse());
                    }
                }else{
                    ViewUtils.showToast(this, listMainResponse.getMessage(), Toast.LENGTH_LONG);
                }

            });
        } else {
            ViewUtils.showToast(this, getString(R.string.offline_message), Toast.LENGTH_SHORT);
        }
    }

    //*** Actions Setup

    @Override
    public void onWeekDayCardClicked(Workouts workout) {
        if (isFromOnGoingWorkoutPlan) {
            Intent intent = new Intent(this, OnGoingWorkoutPlanDayAppointmentsActivity.class);
            intent.putExtra(WORKOUT, workout);
            intent.putExtra(ON_GOING_WORKOUT_PLAN, onGoingWorkoutPlan);
            startActivity(intent);
        }
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
