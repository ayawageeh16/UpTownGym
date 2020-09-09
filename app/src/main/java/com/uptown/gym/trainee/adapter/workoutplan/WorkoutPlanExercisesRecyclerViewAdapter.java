package com.uptown.gym.trainee.adapter.workoutplan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;


import com.uptown.gym.trainee.R;
import com.uptown.gym.trainee.databinding.ItemWorkoutPlanExerciseBinding;
import com.uptown.gym.trainee.listener.ExerciseListener;
import com.uptown.gym.trainee.model.exercise.Exercise;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class WorkoutPlanExercisesRecyclerViewAdapter extends RecyclerView.Adapter<WorkoutPlanExercisesRecyclerViewAdapter.ExercisesViewHolder> {

    private final Context context;
    private List<Exercise> exercises = new ArrayList<>();
    private ExerciseListener.ExercisesInterfaceListener listener;
    private String target;
    private boolean flag;
    private boolean isProgram;


    public WorkoutPlanExercisesRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
        notifyDataSetChanged();
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setProgram(boolean program) {
        isProgram = program;
    }


    public void setListener(ExerciseListener.ExercisesInterfaceListener listener) {
        this.listener = listener;
    }


    public void setTarget(String target) {
        this.target = target;
    }

    @NonNull
    @Override
    public ExercisesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemWorkoutPlanExerciseBinding itemWorkoutPlanExerciseBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_workout_plan_exercise,
                parent,
                false);
        return new ExercisesViewHolder(itemWorkoutPlanExerciseBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ExercisesViewHolder holder, int position) {
        holder.itemWorkoutPlanExerciseBinding.setExercise(exercises.get(position));
        holder.itemWorkoutPlanExerciseBinding.setListener(listener);
        holder.bind(exercises.get(position));
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    class ExercisesViewHolder extends RecyclerView.ViewHolder {

        ItemWorkoutPlanExerciseBinding itemWorkoutPlanExerciseBinding;

        ExercisesViewHolder(@NonNull ItemWorkoutPlanExerciseBinding itemWorkoutPlanExerciseBinding) {
            super(itemWorkoutPlanExerciseBinding.getRoot());
            this.itemWorkoutPlanExerciseBinding = itemWorkoutPlanExerciseBinding;
        }

        void bind(Exercise exercise) {

            // Check if description not empty
            if(exercise.getDescription() != null){
                if (!exercise.getDescription().isEmpty()) {
                    itemWorkoutPlanExerciseBinding.exerciseDescriptionTextView.setVisibility(View.VISIBLE);
                }
            }else {
                itemWorkoutPlanExerciseBinding.exerciseDescriptionTextView.setVisibility(View.GONE);
            }

            // Check if it's class Exercise
            if (target != null) {
                if (target.equals("SINGLE")) {
                    itemWorkoutPlanExerciseBinding.setsAndRepsLayout.setVisibility(View.VISIBLE);
                } else {
                    itemWorkoutPlanExerciseBinding.durationAndSecLayout.setVisibility(View.VISIBLE);
                }
                if (exercise.getRest() != null) {
                    itemWorkoutPlanExerciseBinding.restLayout.setVisibility(View.VISIBLE);
                }
            } else if (isProgram) {  // Program Exercise
                itemWorkoutPlanExerciseBinding.setsOrRoundsLayout.setVisibility(View.VISIBLE);
                itemWorkoutPlanExerciseBinding.restLayout.setVisibility(View.VISIBLE);
            }

        }

    }


}
