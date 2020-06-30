package com.uptown.gym.trainee.adapter.workoutplan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.uptown.gym.trainee.R;
import com.uptown.gym.trainee.databinding.ItemWorkoutPlanWeeksBinding;
import com.uptown.gym.trainee.listener.WorkoutPlansListener;
import com.uptown.gym.trainee.model.workoutplan.WorkoutPlan;
import com.uptown.gym.trainee.model.workoutplan.Workouts;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class WorkoutPlanWeeksRecyclerViewAdapter extends RecyclerView.Adapter<WorkoutPlanWeeksRecyclerViewAdapter.WorkoutsViewHolder> {

    private List<WorkoutPlan> weeks = new ArrayList<>();
    private final Context context;
    private WorkoutPlansListener.WorkoutPlanWeekListener listener;

    public WorkoutPlanWeeksRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setWorkouts(List<WorkoutPlan> weeks) {
        this.weeks = weeks;
        notifyDataSetChanged();
    }

    public void setListener(WorkoutPlansListener.WorkoutPlanWeekListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public WorkoutsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemWorkoutPlanWeeksBinding itemWorkoutPlanWeeksBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_workout_plan_weeks,
                parent,
                false);
        return new WorkoutsViewHolder(itemWorkoutPlanWeeksBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutsViewHolder holder, int position) {
        holder.itemWorkoutPlanWeeksBinding.setWorkoutPlan(weeks.get(position));
        holder.itemWorkoutPlanWeeksBinding.setWorkouts(String.valueOf(countWorkout(weeks.get(position))));
        holder.itemWorkoutPlanWeeksBinding.setListener(listener);
    }

    private int countWorkout(WorkoutPlan workoutPlan) {
        int count = 0;
        for (Workouts workout : workoutPlan.getWorkouts()) {
            if (workout.getName() != null) {
                if (!workout.getName().equals("Rest")) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public int getItemCount() {
        return weeks.size();
    }

    static class WorkoutsViewHolder extends RecyclerView.ViewHolder {

        ItemWorkoutPlanWeeksBinding itemWorkoutPlanWeeksBinding;

        WorkoutsViewHolder(@NonNull ItemWorkoutPlanWeeksBinding itemWorkoutPlanWeeksBinding) {
            super(itemWorkoutPlanWeeksBinding.getRoot());
            this.itemWorkoutPlanWeeksBinding = itemWorkoutPlanWeeksBinding;

        }
    }
}
