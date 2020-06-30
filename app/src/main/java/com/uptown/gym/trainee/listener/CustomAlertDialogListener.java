package com.uptown.gym.trainee.listener;

import android.view.View;

public interface CustomAlertDialogListener {
    default void onViewCreated(View view) {
    }

    void onEnrollClicked();
}
