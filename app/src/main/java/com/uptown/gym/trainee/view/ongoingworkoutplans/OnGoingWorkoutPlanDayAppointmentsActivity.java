package com.uptown.gym.trainee.view.ongoingworkoutplans;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.uptown.gym.trainee.R;
import com.uptown.gym.trainee.adapter.ongoingworkoutplan.OnGoingWorkoutPlanDayAppointmentsRecyclerViewAdapter;
import com.uptown.gym.trainee.databinding.ActivityOnGoingWorkoutPlanDayAppointmentsBinding;
import com.uptown.gym.trainee.listener.CustomAlertDialogListener;
import com.uptown.gym.trainee.listener.OnGoingWorkoutPlanListener;
import com.uptown.gym.trainee.model.ongoingworkoutplan.OnGoingWorkoutPlan;
import com.uptown.gym.trainee.model.ongoingworkoutplan.OnGoingWorkoutPlanDayTime;
import com.uptown.gym.trainee.model.workoutplan.Workouts;
import com.uptown.gym.trainee.util.CustomAlertDialog;
import com.uptown.gym.trainee.util.NetworkConnection;
import com.uptown.gym.trainee.util.SharedPreferencesUtils;
import com.uptown.gym.trainee.util.ViewUtils;
import com.uptown.gym.trainee.view.base.BaseActivity;
import com.uptown.gym.trainee.view.ongoingworkoutplans.fitnesssession.FitnessSessionWorkoutActvity;
import com.uptown.gym.trainee.view.workoutplans.WorkoutPlanWeekDaysActivity;
import com.uptown.gym.trainee.viewmodel.OnGoingWorkoutPlanViewModel;
import com.uptown.gym.trainee.viewmodel.WorkoutPlanViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import static com.uptown.gym.trainee.util.Constants.OnGoingWorkoutPlan.ON_GOING_WORKOUT_PLAN;
import static com.uptown.gym.trainee.util.Constants.OnGoingWorkoutPlan.PLAN_DAY_TIME;
import static com.uptown.gym.trainee.util.Constants.Workout.WORKOUT;

public class OnGoingWorkoutPlanDayAppointmentsActivity extends BaseActivity implements OnGoingWorkoutPlanListener.OnGoingWorkoutPlanDayAppointmentListener, CustomAlertDialogListener {


    private ActivityOnGoingWorkoutPlanDayAppointmentsBinding dataBinding;

    private WorkoutPlanViewModel workoutPlanViewModel;
    private OnGoingWorkoutPlanViewModel onGoingWorkoutPlanViewModel;
    private OnGoingWorkoutPlan onGoingWorkoutPlan;
    private OnGoingWorkoutPlanDayAppointmentsRecyclerViewAdapter adapter;
    private Workouts weekDayWorkout;
    private boolean isSwipeRefresh;
    private OnGoingWorkoutPlanDayTime selectedDayTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_on_going_workout_plan_day_appointments);
        init();
    }

    //*** UI Setup

    private void init() {
        setUpRecyclerView();
        workoutPlanViewModel = new ViewModelProvider(this).get(WorkoutPlanViewModel.class);
        onGoingWorkoutPlanViewModel = new ViewModelProvider(this).get(OnGoingWorkoutPlanViewModel.class);

        Intent intent = getIntent();
        if (intent.hasExtra(ON_GOING_WORKOUT_PLAN) && intent.hasExtra(WORKOUT)) {
            onGoingWorkoutPlan = intent.getParcelableExtra(ON_GOING_WORKOUT_PLAN);
            weekDayWorkout = intent.getParcelableExtra(WORKOUT);
            setupActionBar(weekDayWorkout.getName());
            findAllOnGoingWorkoutPlanDayAppointments();
        }

        dataBinding.setListener(this);

        dataBinding.ongoingWorkoutPlanDayAppointmentsSwipeRefresh.setOnRefreshListener(() -> {
            isSwipeRefresh = true;
            findAllOnGoingWorkoutPlanDayAppointments();
        });
    }

    private void setUpRecyclerView() {
        adapter = new OnGoingWorkoutPlanDayAppointmentsRecyclerViewAdapter();
        dataBinding.ongoingWorkoutPlanDayAppointmentsRecyclerview.setHasFixedSize(true);
        dataBinding.ongoingWorkoutPlanDayAppointmentsRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.ongoingWorkoutPlanDayAppointmentsRecyclerview.setAdapter(adapter);
        adapter.setListener(this);
    }

    private void stopSwipeLayout() {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (dataBinding.ongoingWorkoutPlanDayAppointmentsSwipeRefresh.isRefreshing()) {
                isSwipeRefresh = false;
                dataBinding.ongoingWorkoutPlanDayAppointmentsSwipeRefresh.setRefreshing(false);
            }
        }, 1000);
    }

    //*** Requests Setup

    private void findAllOnGoingWorkoutPlanDayAppointments() {
        if (NetworkConnection.isConnected(this)) {
            if (!isSwipeRefresh) {
                dataBinding.ongoingWorkoutPlanDayAppointmentsProgressBar.setVisibility(View.VISIBLE);
            }
            onGoingWorkoutPlanViewModel.findAllOnGoingWorkoutPlanDayTime(onGoingWorkoutPlan.getWorkoutPlan().getId(),
                    onGoingWorkoutPlan.getId(),
                    weekDayWorkout.getId())
                    .observe(this, listMainResponse -> {
                        dataBinding.ongoingWorkoutPlanDayAppointmentsProgressBar.setVisibility(View.INVISIBLE);
                        stopSwipeLayout();
                        if (listMainResponse.getResponse() != null) {
                            if (listMainResponse.getStatusCode() == 200) {
                                dataBinding.ongoingWorkoutPlanDayAppointmentsRecyclerview.setVisibility(View.VISIBLE);
                                dataBinding.noOngoingWorkoutPlanDayAppointmentsLayout.setVisibility(View.INVISIBLE);
                                adapter.setOnGoingWorkoutPlanDayTimes(listMainResponse.getResponse().getContent());
                            } else {
                                ViewUtils.showToast(OnGoingWorkoutPlanDayAppointmentsActivity.this, listMainResponse.getMessage(), Toast.LENGTH_SHORT);
                            }
                        } else if (listMainResponse.getStatusCode() == 204) {
                            dataBinding.noOngoingWorkoutPlanDayAppointmentsLayout.setVisibility(View.VISIBLE);
                            dataBinding.ongoingWorkoutPlanDayAppointmentsRecyclerview.setVisibility(View.INVISIBLE);
                        } else {
                            ViewUtils.showToast(OnGoingWorkoutPlanDayAppointmentsActivity.this, listMainResponse.getMessage(), Toast.LENGTH_SHORT);
                        }
                    });


        } else {
            ViewUtils.showToast(this, getString(R.string.offline_message), Toast.LENGTH_SHORT);
        }
    }

    private void enrollTraineeToFitnessSessionAPIRequest() {
        if (NetworkConnection.isConnected(this)) {
            dataBinding.ongoingWorkoutPlanDayAppointmentsProgressBar.setVisibility(View.VISIBLE);
            long traineeId = SharedPreferencesUtils.getUserId(this);
            if (traineeId != 0) {
                workoutPlanViewModel.createFitnessSession(onGoingWorkoutPlan.getId(), selectedDayTime.getId(), traineeId)
                        .observe(this, fitnessSessionResponseMainResponse -> {
                            dataBinding.ongoingWorkoutPlanDayAppointmentsProgressBar.setVisibility(View.INVISIBLE);
                            if (fitnessSessionResponseMainResponse.getResponse() != null) {
                                ViewUtils.showToast(OnGoingWorkoutPlanDayAppointmentsActivity.this, getString(R.string.trainee_enrolled_successfullty), Toast.LENGTH_LONG);
                            } else {
                                ViewUtils.showSnack(dataBinding.mainLayout, getString(R.string.you_enrolled_before));
                            }
                        });
            }
        } else {
            ViewUtils.showToast(OnGoingWorkoutPlanDayAppointmentsActivity.this, getString(R.string.offline_message), Toast.LENGTH_SHORT);
        }
    }

    //*** Actions Setup

    @Override
    public void onOnGoingWorkoutPlanDayAppointmentClicked(OnGoingWorkoutPlanDayTime onGoingWorkoutPlanDayTime) {
        Intent intent = new Intent(this, FitnessSessionWorkoutActvity.class);
        intent.putExtra(ON_GOING_WORKOUT_PLAN, onGoingWorkoutPlan);
        intent.putExtra(WORKOUT, weekDayWorkout);
        intent.putExtra(PLAN_DAY_TIME, onGoingWorkoutPlanDayTime);
        startActivity(intent);
    }

    @Override
    public void onEnrollInAppointmentClicked(OnGoingWorkoutPlanDayTime onGoingWorkoutPlanDayTime) {
        selectedDayTime = onGoingWorkoutPlanDayTime;
        CustomAlertDialog customAlertDialog = new CustomAlertDialog(R.layout.dialog_layout);
        customAlertDialog.setListener(this);
        customAlertDialog.showDialog(this);
    }

    @Override
    public void onEnrollClicked() {
        enrollTraineeToFitnessSessionAPIRequest();
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
