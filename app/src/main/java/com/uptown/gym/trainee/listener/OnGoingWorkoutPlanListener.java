package com.uptown.gym.trainee.listener;


import com.uptown.gym.trainee.model.exercise.Exercise;
import com.uptown.gym.trainee.model.ongoingworkoutplan.FitnessSessionTrainee;
import com.uptown.gym.trainee.model.ongoingworkoutplan.OnGoingWorkoutPlan;
import com.uptown.gym.trainee.model.ongoingworkoutplan.OnGoingWorkoutPlanDayTime;

public interface OnGoingWorkoutPlanListener {

    interface AddOnGoingWorkoutPlanListener {
        void onSelectStartTimeClicked();
    }

    interface OnGoingWorkoutPlansListener {
        void onAddOnGoingWorkoutPlanClicked();
    }

    interface OnGoingWorkoutPlansRecyclerViewListener {
        void onOnGoingWorkoutPlanCardClicked(OnGoingWorkoutPlan onGoingWorkoutPlan);
    }

    interface OnOnGoingWorkoutPlanWeekDayListener {
        void onOnGoingWorkoutPlanWeekClicked();
    }

    interface OnGoingWorkoutPlanDayAppointmentListener {
        void onOnGoingWorkoutPlanDayAppointmentClicked(OnGoingWorkoutPlanDayTime onGoingWorkoutPlanDayTime);
        void onEnrollInAppointmentClicked(OnGoingWorkoutPlanDayTime onGoingWorkoutPlanDayTime);
    }

    interface OnGoingWorkoutPlanFitnessSessionListener {
        void onFitnessSessionTraineeCardClicked(FitnessSessionTrainee trainee);
    }

    interface FitnessSessionTraineeExercisesListener {
        void onFitnessSessionWodExerciseCardClicked(Exercise exercise);
    }


}
