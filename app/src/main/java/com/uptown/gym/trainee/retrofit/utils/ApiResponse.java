package com.uptown.gym.trainee.retrofit.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.uptown.gym.trainee.model.base.MainResponse;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ApiResponse<ResponseType> implements Callback<ResponseType> {

    private ApiResponseListener<ResponseType> listener;

    public ApiResponse(ApiResponseListener<ResponseType> listener) {
        this.listener = listener;
    }

    @Override
    public void onResponse(@NotNull Call<ResponseType> call, Response<ResponseType> response) {
        if (response.isSuccessful()) {
            if (response.body() != null) {
                MainResponse<ResponseType> mainResponse = new MainResponse<>();
                mainResponse.setStatusCode(response.code());
                mainResponse.setResponse(response.body());
                listener.onSuccess((ResponseType) mainResponse);
            } else if (response.code() == 204) {
                MainResponse<ResponseType> mainResponse = new MainResponse<>();
                mainResponse.setStatusCode(response.code());
                mainResponse.setResponse(null);
                listener.onSuccess((ResponseType) mainResponse);
            }
        } else {
            // Parse Retrofit Error body
            if (response.errorBody() != null) {
                ErrorResponse errorResponse = null;
                MainResponse<ResponseType> mainResponse = new MainResponse<>();

                try {
                    errorResponse = new Gson().fromJson(response.errorBody().charStream(), ErrorResponse.class);
                    mainResponse = new MainResponse<>();
                    if (errorResponse != null) {
                        if (errorResponse.getDetail() != null) {
                            mainResponse.setMessage(errorResponse.getDetail());
                        }
                    } else {
                        mainResponse.setMessage("Connection Error");
                    }
                } catch (IllegalStateException | JsonSyntaxException exception) {
                    Log.d("JsonSyntaxException", exception.getMessage());
                }


                if (errorResponse != null) {
                    mainResponse.setStatusCode(errorResponse.getStatus());
                    listener.onSuccess((ResponseType) mainResponse);
                }

            }

        }
    }

    @Override
    public void onFailure(Call<ResponseType> call, Throwable t) {
        if (listener != null) {
            if (t instanceof IOException) {
                listener.onFailure("Network Failure");
            } else {
                listener.onFailure(t.getMessage());
            }
        }
    }
}
