package com.uptown.gym.trainee.view.ongoingworkoutplans.fitnesssession;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.uptown.gym.trainee.R;
import com.uptown.gym.trainee.adapter.workoutplan.WorkoutPlanExercisesTypeRecyclerViewAdapter;
import com.uptown.gym.trainee.databinding.ActivityFitnessSessionWorkoutActvityBinding;
import com.uptown.gym.trainee.model.ongoingworkoutplan.OnGoingWorkoutPlan;
import com.uptown.gym.trainee.model.ongoingworkoutplan.OnGoingWorkoutPlanDayTime;
import com.uptown.gym.trainee.model.workoutplan.Workouts;
import com.uptown.gym.trainee.util.NetworkConnection;
import com.uptown.gym.trainee.util.ViewUtils;
import com.uptown.gym.trainee.view.base.BaseActivity;
import com.uptown.gym.trainee.viewmodel.WorkoutPlanViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import static com.uptown.gym.trainee.util.Constants.OnGoingWorkoutPlan.ON_GOING_WORKOUT_PLAN;
import static com.uptown.gym.trainee.util.Constants.OnGoingWorkoutPlan.PLAN_DAY_TIME;
import static com.uptown.gym.trainee.util.Constants.Workout.WORKOUT;

public class FitnessSessionWorkoutActvity extends BaseActivity {

    private ActivityFitnessSessionWorkoutActvityBinding dataBinding;
    private WorkoutPlanExercisesTypeRecyclerViewAdapter adapter;

    private OnGoingWorkoutPlan onGoingWorkoutPlan;
    private Workouts workout;
    private OnGoingWorkoutPlanDayTime onGoingWorkoutPlanDayTime;
    private boolean isSwipeRefresh;
    private WorkoutPlanViewModel workoutPlanViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_fitness_session_workout_actvity);
        getExtras();
        init();
    }

    //*** UI Setup

    private void getExtras() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra(ON_GOING_WORKOUT_PLAN)) {
                onGoingWorkoutPlan = intent.getParcelableExtra(ON_GOING_WORKOUT_PLAN);
                workout = intent.getParcelableExtra(WORKOUT);
                onGoingWorkoutPlanDayTime = intent.getParcelableExtra(PLAN_DAY_TIME);
            }
        }
    }

    private void init() {
        setupActionBar(getString(R.string.fitness_session_workout));
        workoutPlanViewModel = new ViewModelProvider(this).get(WorkoutPlanViewModel.class);

        setupRecyclerView();
        if (workout != null) {
            findAllWorkoutExercisesRequest();
        }

        dataBinding.fragmentFitnessSessionWorkoutSwipe.setOnRefreshListener(() -> {
            isSwipeRefresh = true;
            findAllWorkoutExercisesRequest();
        });
    }

    private void setupRecyclerView() {
        adapter = new WorkoutPlanExercisesTypeRecyclerViewAdapter(this);
        adapter.setFlag(false);
        dataBinding.fragmentFitnessSessionRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.fragmentFitnessSessionRecyclerView.setHasFixedSize(true);
        dataBinding.fragmentFitnessSessionRecyclerView.setAdapter(adapter);
        adapter.setTarget(onGoingWorkoutPlan.getTarget());
    }

    private void stopSwipeLayout() {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (dataBinding.fragmentFitnessSessionWorkoutSwipe.isRefreshing()) {
                isSwipeRefresh = false;
                dataBinding.fragmentFitnessSessionWorkoutSwipe.setRefreshing(false);
            }
        }, 1000);
    }


    // *** Requests Setup

    private void findAllWorkoutExercisesRequest() {
        if (NetworkConnection.isConnected(this)) {
            if (!isSwipeRefresh) {
                dataBinding.fragmentFitnessSessionWorkoutsProgressBar.setVisibility(View.VISIBLE);
            }
            workoutPlanViewModel.findAllWorkoutExercises(workout.getId()).observe(this, mainResponseMainResponse -> {
                dataBinding.fragmentFitnessSessionWorkoutsProgressBar.setVisibility(View.INVISIBLE);
                stopSwipeLayout();
                if (mainResponseMainResponse.getResponse() != null) {
                    if (mainResponseMainResponse.getStatusCode() == 200) {
                        adapter.setExercises(mainResponseMainResponse.getResponse());
                        dataBinding.fragmentFitnessSessionNoWorkoutsLayout.setVisibility(View.INVISIBLE);
                        dataBinding.fragmentFitnessSessionRecyclerView.setVisibility(View.VISIBLE);
                    } else {
                        ViewUtils.showToast(FitnessSessionWorkoutActvity.this, mainResponseMainResponse.getMessage(), Toast.LENGTH_SHORT);
                    }
                } else if (mainResponseMainResponse.getStatusCode() == 204) {
                    dataBinding.fragmentFitnessSessionNoWorkoutsLayout.setVisibility(View.VISIBLE);
                    dataBinding.fragmentFitnessSessionRecyclerView.setVisibility(View.INVISIBLE);
                } else {
                    ViewUtils.showToast(FitnessSessionWorkoutActvity.this, mainResponseMainResponse.getMessage(), Toast.LENGTH_SHORT);
                }
            });
        } else {
            ViewUtils.showToast(FitnessSessionWorkoutActvity.this, getString(R.string.no_connection), Toast.LENGTH_SHORT);
        }
    }

    // *** Actions Setup
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
