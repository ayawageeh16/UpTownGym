package com.uptown.gym.trainee.adapter.workoutplan;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.uptown.gym.trainee.R;
import com.uptown.gym.trainee.databinding.ItemWorkoutPlanWeekDayBinding;
import com.uptown.gym.trainee.listener.WorkoutPlansListener;
import com.uptown.gym.trainee.model.workoutplan.Workouts;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class WorkoutPlanWeekDaysRecyclerViewAdapter extends RecyclerView.Adapter<WorkoutPlanWeekDaysRecyclerViewAdapter.WorkoutPlanWeekDaysViewHolder> {

    private List<Workouts> weekWorkoutDays = new ArrayList<>();
    private WorkoutPlansListener.WorkoutPlanWeekDayListener listener;

    public void setWeekWorkoutDays(List<Workouts> weekWorkoutDay) {
        this.weekWorkoutDays = weekWorkoutDay;
        notifyDataSetChanged();
    }

    public void setListener(WorkoutPlansListener.WorkoutPlanWeekDayListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public WorkoutPlanWeekDaysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemWorkoutPlanWeekDayBinding itemWorkoutPlanWeekBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_workout_plan_week_day,
                parent,
                false);
        return new WorkoutPlanWeekDaysViewHolder(itemWorkoutPlanWeekBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutPlanWeekDaysViewHolder holder, int position) {
        holder.itemWorkoutPlanWeekDayBinding.setWeekDayWorkout(weekWorkoutDays.get(position));
        holder.itemWorkoutPlanWeekDayBinding.setListener(listener);

    }


    @Override
    public int getItemCount() {
        return weekWorkoutDays.size();
    }

    static class WorkoutPlanWeekDaysViewHolder extends RecyclerView.ViewHolder {

        ItemWorkoutPlanWeekDayBinding itemWorkoutPlanWeekDayBinding;

        WorkoutPlanWeekDaysViewHolder(@NonNull ItemWorkoutPlanWeekDayBinding itemWorkoutPlanWeekDayBinding) {
            super(itemWorkoutPlanWeekDayBinding.getRoot());
            this.itemWorkoutPlanWeekDayBinding = itemWorkoutPlanWeekDayBinding;
        }
    }
}
