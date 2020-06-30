package com.uptown.gym.trainee.adapter.workoutplan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.uptown.gym.trainee.R;
import com.uptown.gym.trainee.databinding.ItemWorkoutPlanCategoryBinding;
import com.uptown.gym.trainee.listener.WorkoutPlansListener;
import com.uptown.gym.trainee.model.workoutplan.WorkoutPlansResponse;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WorkoutPlanCategoryRecyclerViewAdapter extends RecyclerView.Adapter<WorkoutPlanCategoryRecyclerViewAdapter.WorkoutPlanCategoryViewHolder> {

    private Context context;
    private WorkoutPlansListener.WorkoutPlanCategoryListener categoryListener;

    private List<WorkoutPlansResponse> workoutPlans = new ArrayList<>();

    public void setCategoryListener(WorkoutPlansListener.WorkoutPlanCategoryListener categoryListener) {
        this.categoryListener = categoryListener;
    }

    public void setWorkoutPlans(List<WorkoutPlansResponse> workoutPlans) {
        this.workoutPlans = workoutPlans;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WorkoutPlanCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ItemWorkoutPlanCategoryBinding itemWorkoutPlanCategoryBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_workout_plan_category, parent, false);
        return new WorkoutPlanCategoryViewHolder(itemWorkoutPlanCategoryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutPlanCategoryViewHolder holder, int position) {
        holder.itemWorkoutPlanCategoryBinding.setCategory(workoutPlans.get(position).getCategory());
        holder.itemWorkoutPlanCategoryBinding.setListener(categoryListener);

        WorkoutPlansRecyclerViewAdapter workoutPlansRecyclerViewAdapter = new WorkoutPlansRecyclerViewAdapter();
        holder.itemWorkoutPlanCategoryBinding.workoutPlansRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.itemWorkoutPlanCategoryBinding.workoutPlansRecyclerView.setAdapter(workoutPlansRecyclerViewAdapter);
        workoutPlansRecyclerViewAdapter.setWorkoutPlanResponses(workoutPlans.get(position).getWorkoutPlans().getContent());
        holder.itemWorkoutPlanCategoryBinding.workoutPlansRecyclerView.setHasFixedSize(true);

    }

    @Override
    public int getItemCount() {
        return workoutPlans.size();
    }

    static class WorkoutPlanCategoryViewHolder extends RecyclerView.ViewHolder {
        ItemWorkoutPlanCategoryBinding itemWorkoutPlanCategoryBinding;

        public WorkoutPlanCategoryViewHolder(@NonNull ItemWorkoutPlanCategoryBinding itemWorkoutPlanCategoryBinding) {
            super(itemWorkoutPlanCategoryBinding.getRoot());
            this.itemWorkoutPlanCategoryBinding = itemWorkoutPlanCategoryBinding;
        }
    }
}
