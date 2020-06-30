package com.uptown.gym.trainee.listener;


import com.uptown.gym.trainee.model.exercise.Exercise;

public interface ExerciseListener {

    interface ExercisesListener {
        void onAddExerciseClicked();
    }

    interface ExercisesInterfaceListener {
        void onDeleteExerciseClicked(Exercise exercise);

        void onEditExerciseClicked(Exercise exercise);

        void onExerciseCardClicked(Exercise exercise);
    }

}
