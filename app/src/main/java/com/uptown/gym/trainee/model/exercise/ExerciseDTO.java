package com.uptown.gym.trainee.model.exercise;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseDTO {

    private List<Exercise> exercises;
    private int code;

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
