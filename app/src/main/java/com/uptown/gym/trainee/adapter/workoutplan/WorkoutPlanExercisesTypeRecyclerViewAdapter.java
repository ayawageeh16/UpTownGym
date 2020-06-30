package com.uptown.gym.trainee.adapter.workoutplan;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.uptown.gym.trainee.R;
import com.uptown.gym.trainee.databinding.ItemExerciseTypeBinding;
import com.uptown.gym.trainee.listener.ExerciseListener;
import com.uptown.gym.trainee.model.exercise.Exercise;
import com.uptown.gym.trainee.model.exercise.WorkoutExercisesResponse;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WorkoutPlanExercisesTypeRecyclerViewAdapter extends RecyclerView.Adapter<WorkoutPlanExercisesTypeRecyclerViewAdapter.ExercisesViewHolder> {

    private List<WorkoutExercisesResponse> exercises = new ArrayList<>();
    private final Context context;
    private ExerciseListener.ExercisesInterfaceListener listener;
    private String target;
    private boolean flag;
    private boolean isProgram;

    public WorkoutPlanExercisesTypeRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setExercises(List<WorkoutExercisesResponse> exercises) {
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
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExercisesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemExerciseTypeBinding itemExerciseTypeBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_exercise_type, parent, false);
        return new ExercisesViewHolder(itemExerciseTypeBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ExercisesViewHolder holder, int position) {
        if (exercises.get(position).getExercises().getContent().size() != 0) {
            switch (exercises.get(position).getExerciseType()) {
                case "WARMING_UP":
                    holder.itemExerciseTypeBinding.setExerciseType(context.getString(R.string.warming_up));
                    break;
                case "CORE":
                    holder.itemExerciseTypeBinding.setExerciseType(context.getString(R.string.core));
                    break;
                case "WOD":
                    holder.itemExerciseTypeBinding.setExerciseType(context.getString(R.string.wod));
                    break;
                case "COOLING_DOWN":
                    holder.itemExerciseTypeBinding.setExerciseType(context.getString(R.string.cooling_down));
                    break;
                default:
                    holder.itemExerciseTypeBinding.setExerciseType(" ");
            }
        }

        WorkoutPlanExercisesRecyclerViewAdapter exerciseAdapter = new WorkoutPlanExercisesRecyclerViewAdapter(context);
        exerciseAdapter.setFlag(flag);
        exerciseAdapter.setTarget(target);
        exerciseAdapter.setProgram(isProgram);
        holder.itemExerciseTypeBinding.nestedExercisesRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.itemExerciseTypeBinding.nestedExercisesRecyclerView.setAdapter(exerciseAdapter);
        holder.itemExerciseTypeBinding.nestedExercisesRecyclerView.setHasFixedSize(true);

        List<Exercise> currentExercises = exercises.get(position).getExercises().getContent();
        exerciseAdapter.setExercises(currentExercises);
        exerciseAdapter.setListener(listener);
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }


    static class ExercisesViewHolder extends RecyclerView.ViewHolder {

        ItemExerciseTypeBinding itemExerciseTypeBinding;

        private ExercisesViewHolder(@NonNull ItemExerciseTypeBinding itemExerciseTypeBinding) {
            super(itemExerciseTypeBinding.getRoot());
            this.itemExerciseTypeBinding = itemExerciseTypeBinding;
        }
    }
}
