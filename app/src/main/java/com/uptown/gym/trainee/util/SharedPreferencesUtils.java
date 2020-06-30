package com.uptown.gym.trainee.util;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferencesUtils {

    public static long getUserId(Context context) {
        SharedPreferences settings = context.getSharedPreferences(Constants.User.USER_DATA, MODE_PRIVATE);
        return settings.getLong(Constants.User.USER_ID, 0);
    }

    public static String getUserGender(Context context) {
        SharedPreferences settings = context.getSharedPreferences(Constants.User.USER_DATA, MODE_PRIVATE);
        return settings.getString(Constants.User.GENDER, "");
    }

}
