package com.uptown.gym.trainee.model.workoutplan;

public enum Category {
    BUILD_MUSCLE("Build Muscle"),
    WEIGHT_LOSS("Weight Loss"),
    GAIN_STRENGTH("Gain Strength"),
    GET_FIT("Get Fit"),
    PERFORMANCE("Performance");

    private final String value;

    Category(final String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
