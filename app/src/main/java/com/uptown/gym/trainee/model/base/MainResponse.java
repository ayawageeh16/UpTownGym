package com.uptown.gym.trainee.model.base;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

/**
 * Generic Response Class Model
 */
public class MainResponse<T> {

    private int statusCode;

    private String message;

    private T response;

}
