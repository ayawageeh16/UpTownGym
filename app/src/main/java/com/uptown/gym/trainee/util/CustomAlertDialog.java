package com.uptown.gym.trainee.util;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.uptown.gym.trainee.R;
import com.uptown.gym.trainee.listener.CustomAlertDialogListener;

public class CustomAlertDialog {

    private int layout;
    private CustomAlertDialogListener listener;

    public CustomAlertDialog(int layout) {
        this.layout = layout;
    }

    public void setListener(CustomAlertDialogListener listener) {
        this.listener = listener;
    }

    public void showDialog(Activity activity) {
        View view = LayoutInflater.from(activity).inflate(layout, null);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(activity, R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog)
                .setView(view)
                .setNegativeButton(activity.getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(activity.getString(R.string.enroll), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onEnrollClicked();
                    }
                });
        builder.show();
    }

}
