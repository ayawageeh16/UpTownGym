package com.uptown.gym.trainee.listener;

import com.uptown.gym.trainee.model.workoutplan.WorkoutPlan;
import com.uptown.gym.trainee.model.workoutplan.WorkoutPlanResponse;
import com.uptown.gym.trainee.model.workoutplan.Workouts;

public interface WorkoutPlansListener {


    interface WorkoutPlanListener {
        void onWorkoutPlanClicked(WorkoutPlan workoutPlan);
    }

    interface WorkoutPlanWeekListener {
        void onWeekCardClicked(WorkoutPlan workoutPlan);
    }

    interface WorkoutPlanWeekDayListener {
        void onWeekDayCardClicked(Workouts workouts);
    }

    interface WorkoutPlanDayWorkoutListener {
        void onWorkoutCardClicked();
    }

    interface WorkoutPlanCategoryListener {
        void onMoreClicked(String category);
    }


}
