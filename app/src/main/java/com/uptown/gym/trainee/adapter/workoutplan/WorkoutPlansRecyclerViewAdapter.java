package com.uptown.gym.trainee.adapter.workoutplan;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.uptown.gym.trainee.R;
import com.uptown.gym.trainee.databinding.ItemWorkoutPlanBinding;
import com.uptown.gym.trainee.model.workoutplan.WorkoutPlanResponse;
import com.uptown.gym.trainee.view.workoutplans.WorkoutPlanDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class WorkoutPlansRecyclerViewAdapter extends RecyclerView.Adapter<WorkoutPlansRecyclerViewAdapter.WorkoutPlansViewHolder> {

    public static final String WORKOUT_PLAN_EXTRA = "WORKOUT_PLAN";

    private List<WorkoutPlanResponse> workoutPlanResponses = new ArrayList<>();
    private Context context;


    public void setWorkoutPlanResponses(List<WorkoutPlanResponse> workoutPlanResponses) {
        this.workoutPlanResponses = workoutPlanResponses;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WorkoutPlansViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        ItemWorkoutPlanBinding workoutPlanItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_workout_plan,
                parent,
                false);
        return new WorkoutPlansViewHolder(workoutPlanItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutPlansViewHolder holder, int position) {
        holder.workoutPlanItemBinding.setWorkoutPlan(workoutPlanResponses.get(position));
        switch (workoutPlanResponses.get(position).getCategory()) {
            case "Build Muscle":
                holder.workoutPlanItemBinding.mainLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.build_muscle));
                break;
            case "Gain Strength":
                holder.workoutPlanItemBinding.mainLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.gain_strength));
                break;
            case "Get Fit":
                holder.workoutPlanItemBinding.mainLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.get_fit));
                break;
            case "Weight Loss":
                holder.workoutPlanItemBinding.mainLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.weight_oss));
                break;
            default:
                holder.workoutPlanItemBinding.mainLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.performance));
                break;
        }

    }

    @Override
    public int getItemCount() {
        return workoutPlanResponses.size();
    }

    class WorkoutPlansViewHolder extends RecyclerView.ViewHolder {

        ItemWorkoutPlanBinding workoutPlanItemBinding;

        WorkoutPlansViewHolder(@NonNull ItemWorkoutPlanBinding workoutPlanItemBinding) {
            super(workoutPlanItemBinding.getRoot());
            this.workoutPlanItemBinding = workoutPlanItemBinding;

            // Card Click Action
            workoutPlanItemBinding.workoutPlanItemCard.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    WorkoutPlanResponse currentWorkoutPlan = workoutPlanResponses.get(position);
                    Intent intent = new Intent(context, WorkoutPlanDetailsActivity.class);
                    intent.putExtra(WORKOUT_PLAN_EXTRA, currentWorkoutPlan);
                    context.startActivity(intent);
                }
            });
        }
    }

}


