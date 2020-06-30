package com.uptown.gym.trainee.view;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.uptown.gym.trainee.BuildConfig;
import com.uptown.gym.trainee.R;
import com.uptown.gym.trainee.util.Constants;
import com.uptown.gym.trainee.view.registeration.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_TIME_OUT = 2500;
    private static final String TAG = "Creating channel ->> ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        if (BuildConfig.DEBUG) {
            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(false);
        }

        // Handler for Splash Image Display
        new Handler().postDelayed(() -> {
            // Check if user already logged in
            if (isAlreadyLoggedIn()) {
                Intent startHomeActivity = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(startHomeActivity);
                finish();
            } else {
                Intent startLoginActivity = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(startLoginActivity);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    protected void onResume() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        super.onResume();
    }

    private boolean isAlreadyLoggedIn() {
        SharedPreferences settings = getSharedPreferences(Constants.User.USER_DATA, MODE_PRIVATE);
        return settings.contains(Constants.User.USER_NAME);
    }

    private void createNotificationChannel() {
        Log.d(TAG, "Creating channel");

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(Constants.Notification.CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
