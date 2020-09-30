package com.uptown.gym.trainee.view.ongoingworkoutplans;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.uptown.gym.trainee.R;
import com.uptown.gym.trainee.adapter.ongoingworkoutplan.OnGoingWorkoutPlansRecyclerViewAdapter;
import com.uptown.gym.trainee.databinding.FragmentOngoingWorkoutPlansBinding;
import com.uptown.gym.trainee.listener.OnGoingWorkoutPlanListener;
import com.uptown.gym.trainee.model.ongoingworkoutplan.OnGoingWorkoutPlan;
import com.uptown.gym.trainee.util.NetworkConnection;
import com.uptown.gym.trainee.util.SharedPreferencesUtils;
import com.uptown.gym.trainee.util.ViewUtils;
import com.uptown.gym.trainee.viewmodel.OnGoingWorkoutPlanViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import static android.app.Activity.RESULT_OK;
import static com.uptown.gym.trainee.util.Constants.OnGoingWorkoutPlan.ONGOING_WORKOUT_PLAN;

public class OnGoingWorkoutPlansFragment extends Fragment implements OnGoingWorkoutPlanListener.OnGoingWorkoutPlansRecyclerViewListener {

    static final int EDIT_ONGOING_WORKOUT_PLAN_RESULT = 400;

    private FragmentOngoingWorkoutPlansBinding dataBinding;
    private OnGoingWorkoutPlansRecyclerViewAdapter adapter;
    private OnGoingWorkoutPlanViewModel onGoingWorkoutPlanViewModel;
    private List<OnGoingWorkoutPlan> ongoingWorkoutPlans = new ArrayList<>();
    private boolean isSwipeRefresh;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_ongoing_workout_plans, container, false);
        init();

        return dataBinding.getRoot();
    }

    //*** UI Setup

    private void init() {
        setUpRecyclerView();
        onGoingWorkoutPlanViewModel = new ViewModelProvider(this).get(OnGoingWorkoutPlanViewModel.class);
        findAllOnGoingWorkoutPlansRequest();

        dataBinding.womenOngoingWorkoutPlansSwipeRefresh.setOnRefreshListener(() -> {
            isSwipeRefresh = true;
            findAllOnGoingWorkoutPlansRequest();
        });
    }

    private void setUpRecyclerView() {
        adapter = new OnGoingWorkoutPlansRecyclerViewAdapter(getContext());
        dataBinding.fragmentWomenOngoingWorkoutPlansRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataBinding.fragmentWomenOngoingWorkoutPlansRecyclerView.setHasFixedSize(true);
        dataBinding.fragmentWomenOngoingWorkoutPlansRecyclerView.setAdapter(adapter);
        adapter.setListener(this);
    }

    public void refresh() {
        findAllOnGoingWorkoutPlansRequest();
    }

    private void stopSwipeLayout() {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (dataBinding.womenOngoingWorkoutPlansSwipeRefresh.isRefreshing()) {
                isSwipeRefresh = false;
                dataBinding.womenOngoingWorkoutPlansSwipeRefresh.setRefreshing(false);
            }
        }, 1000);
    }

    //*** Requests Setup

    private void findAllOnGoingWorkoutPlansRequest() {
        if (NetworkConnection.isConnected(getContext())) {
            if (!isSwipeRefresh) {
                dataBinding.fragmentWomenOngoingWorkoutPlansProgressBar.setVisibility(View.VISIBLE);
            }
            String gender = SharedPreferencesUtils.getUserGender(getContext());
            long traineeId = SharedPreferencesUtils.getUserId(getContext());
            if (!gender.isEmpty()) {
                onGoingWorkoutPlanViewModel.findAllOnGoingWorkoutPlansByGender(traineeId).observe(getViewLifecycleOwner(), onGoingWorkoutPlansResponseMainResponse -> {
                    dataBinding.fragmentWomenOngoingWorkoutPlansProgressBar.setVisibility(View.INVISIBLE);
                    stopSwipeLayout();
                    if (onGoingWorkoutPlansResponseMainResponse.getResponse() != null) {
                        if (onGoingWorkoutPlansResponseMainResponse.getStatusCode() == 200) {
                            dataBinding.noWomenOngoingWorkoutPlansLayout.setVisibility(View.INVISIBLE);
                            dataBinding.fragmentWomenOngoingWorkoutPlansRecyclerView.setVisibility(View.VISIBLE);
                            ongoingWorkoutPlans = onGoingWorkoutPlansResponseMainResponse.getResponse().getOnGoingWorkoutPlans();
                            adapter.setOngoingWorkoutPlans(ongoingWorkoutPlans);
                        } else if (onGoingWorkoutPlansResponseMainResponse.getStatusCode() == 204) {
                            dataBinding.fragmentWomenOngoingWorkoutPlansRecyclerView.setVisibility(View.INVISIBLE);
                            dataBinding.noWomenOngoingWorkoutPlansLayout.setVisibility(View.VISIBLE);
                        } else {
                            ViewUtils.showToast(getContext(), onGoingWorkoutPlansResponseMainResponse.getMessage(), Toast.LENGTH_SHORT);
                        }
                    } else {
                        ViewUtils.showToast(getContext(), onGoingWorkoutPlansResponseMainResponse.getMessage(), Toast.LENGTH_SHORT);
                    }
                });
            } else {
                ViewUtils.showToast(getContext(), getString(R.string.no_connection), Toast.LENGTH_SHORT);
            }
        }
    }

    //*** Actions Setup

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_ONGOING_WORKOUT_PLAN_RESULT && resultCode == RESULT_OK) {
            findAllOnGoingWorkoutPlansRequest();
        }
    }

    @Override
    public void onOnGoingWorkoutPlanCardClicked(OnGoingWorkoutPlan onGoingWorkoutPlan) {
        Intent intent = new Intent(getContext(), OngoingWorkoutPlanDetailsActivity.class);
        intent.putExtra(ONGOING_WORKOUT_PLAN, onGoingWorkoutPlan);
        startActivity(intent);
    }
}
