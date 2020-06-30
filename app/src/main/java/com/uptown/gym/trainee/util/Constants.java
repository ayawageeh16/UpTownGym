package com.uptown.gym.trainee.util;

public final class Constants {

    public abstract static class APIResponse {
        public static final int HTTP_STATUS_OK = 200;
        public static final int HTTP_STATUS_CREATED = 201;
        public static final int HTTP_STATUS_NO_CONTENT = 204;
        public static final int HTTP_STATUS_BAD_REQUEST = 400;
        public static final int HTTP_STATUS_CONFLICT = 409;
    }

    public abstract static class Notification {
        // Notification Channel
        public static final String CHANNEL_ID = "100";
    }

    public abstract static class User {
        // Shared Preferences Constants
        public static final String USER_DATA = "USER_DATA";
        public static final String USER_NAME = "USER_NAME";
        public static final String USER_PASSWORD = "USER_PASSWORD";
        public static final String USER_ID = "USER_ID";
        public static final String GENDER = "GENDER";
    }

    public abstract static class WorkoutPlan {
        public static final String CATEGORY = "CATEGORY";
        public static final String IS_CATEGORY_WORKOUT_PLAN = "IS_CATEGORY_WORKOUT_PLAN";
    }

    public abstract static class Workout {
        public static final String PLAN_DAY_WORKOUT = "PLAN_DAY_WORKOUT";
        public static final String PLAN_DAY_WORKOUT_ID = "PLAN_DAY_WORKOUT_ID";
        public static final String WORKOUT_EXERCISE = "WORKOUT_EXERCISE";
        public static final String WORKOUT_PLAN_WEEK = "WORKOUT_PLAN_WEEK";
        public static final String WORKOUT_PLAN_WEEK_LIST = "WORKOUT_PLAN_WEEK_LIST";
        public static final String WORKOUT = "WORKOUT";
        public static final String WORKOUT_PLAN = "WORKOUT_PLAN";


    }

    public abstract static class OnGoingWorkoutPlan {
        public static final String ONGOING_WORKOUT_PLAN = "ONGOING_WORKOUT_PLAN";
        public static final String ON_GOING_WORKOUT_PLAN = "ON_GOING_WORKOUT_PLAN";
        public static final String WEEK_WORKOUT = "WEEK_WORKOUT";
        public static final String WEEK_WORKOUT_DAYS_LIST = "WEEK_WORKOUT_DAYS_LIST";
        public static final String PLAN_DAY_TIME = "PLAN_DAY_TIME";
        public static final String IS_ONGOING = "IS_ONGOING";
    }

    public abstract static class Exercise {
        public static final String EXERCISE = "EXERCISE";

    }

    public abstract static class Trainee {
        public static final String TRAINEE = "TRAINEE";
        public static final String FITNESS_TEST = "FITNESS_TEST";
        public static final String INBODY = "INBODY";

    }

}
