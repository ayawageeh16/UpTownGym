package com.uptown.gym.trainee.view.workoutplans;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.uptown.gym.trainee.R;
import com.uptown.gym.trainee.adapter.workoutplan.WorkoutPlanCategoryRecyclerViewAdapter;
import com.uptown.gym.trainee.databinding.FragmentWorkoutPlansBinding;
import com.uptown.gym.trainee.listener.WorkoutPlansListener;
import com.uptown.gym.trainee.model.workoutplan.WorkoutPlanResponse;
import com.uptown.gym.trainee.util.Constants;
import com.uptown.gym.trainee.util.NetworkConnection;
import com.uptown.gym.trainee.util.ViewUtils;
import com.uptown.gym.trainee.viewmodel.WorkoutPlanViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

public class WorkoutPlansFragment extends Fragment implements WorkoutPlansListener.WorkoutPlanCategoryListener {

    private WorkoutPlanCategoryRecyclerViewAdapter adapter;
    private WorkoutPlanViewModel workoutPlanViewModel;
    private boolean isSwipeRefresh;

    private FragmentWorkoutPlansBinding dataBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_workout_plans, container, false);
        init();
        return dataBinding.getRoot();
    }

    @Override
    public void onResume() {
        findAllWorkoutPlansRequest();
        super.onResume();
    }

    //*** UI Setup

    private void init() {
        setUpRecyclerView();
        workoutPlanViewModel = new ViewModelProvider(this).get(WorkoutPlanViewModel.class);

        dataBinding.workoutPlansFragmentSwipeRefresh.setOnRefreshListener(() -> {
            isSwipeRefresh = true;
            findAllWorkoutPlansRequest();
        });
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
        adapter = new WorkoutPlanCategoryRecyclerViewAdapter();
        dataBinding.workoutPlansRecyclerView.setHasFixedSize(true);
        dataBinding.workoutPlansRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataBinding.workoutPlansRecyclerView.setAdapter(adapter);
        adapter.setCategoryListener(this);

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

    private void findAllWorkoutPlansRequest() {
        if (NetworkConnection.isConnected(getContext())) {
            if (!isSwipeRefresh) {
                dataBinding.fragmentWorkoutPlansProgressBar.setVisibility(View.VISIBLE);
            }
            workoutPlanViewModel.findAllWorkoutPlans().observe(getViewLifecycleOwner(), workoutPlansResponseMainResponse -> {
                dataBinding.fragmentWorkoutPlansProgressBar.setVisibility(View.INVISIBLE);
                stopSwipeLayout();
                if (workoutPlansResponseMainResponse != null) {
                    if (workoutPlansResponseMainResponse.getStatusCode() == 200) {
                        dataBinding.workoutPlansRecyclerView.setVisibility(View.VISIBLE);
                        dataBinding.fragmentNoWorkoutPlansLayout.setVisibility(View.INVISIBLE);
                        adapter.setWorkoutPlans(workoutPlansResponseMainResponse.getResponse());
                    } else if (workoutPlansResponseMainResponse.getStatusCode() == 204) {
                        dataBinding.fragmentNoWorkoutPlansLayout.setVisibility(View.VISIBLE);
                        dataBinding.workoutPlansRecyclerView.setVisibility(View.INVISIBLE);
                    }
                } else {
                    ViewUtils.showToast(getContext(), workoutPlansResponseMainResponse.getMessage(), Toast.LENGTH_LONG);
                }
            });
        } else {
            ViewUtils.showToast(getContext(), getString(R.string.offline_message), Toast.LENGTH_SHORT);
        }
    }

    //*** Actions Setup


    @Override
    public void onMoreClicked(String category) {
        Intent intent = new Intent(getContext(), WorkoutPlanCategoryActivity.class);
        intent.putExtra(Constants.WorkoutPlan.CATEGORY, category);
        startActivity(intent);
    }

}