package com.uptown.gym.trainee.util;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public final class ViewUtils {

    private ViewUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static void showToast(Context context, String message, int duration) {
        Toast.makeText(context, message, duration).show();
    }

    public static void disableUI(Activity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public static void enableUI(Activity activity) {
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public static void showSnack(View mainLayout, String message) {
        Snackbar snackbar = Snackbar.make(mainLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
