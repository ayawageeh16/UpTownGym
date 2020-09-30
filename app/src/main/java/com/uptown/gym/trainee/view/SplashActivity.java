package com.uptown.gym.trainee.view;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.ActivityResult;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.uptown.gym.trainee.BuildConfig;
import com.uptown.gym.trainee.R;
import com.uptown.gym.trainee.util.Constants;
import com.uptown.gym.trainee.view.registeration.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import static com.crashlytics.android.Crashlytics.log;
import static com.google.android.play.core.install.model.AppUpdateType.IMMEDIATE;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_TIME_OUT = 2500;
    private static final String TAG = "Creating channel ->> ";
    private AppUpdateManager appUpdateManager;
    private static final int MY_REQUEST_CODE = 50;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        appUpdateManager = AppUpdateManagerFactory.create(this);

        if (BuildConfig.DEBUG) {
            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(false);
        }

//        // Handler for Splash Image Display
        new Handler().postDelayed(this::checkForUpdates, SPLASH_TIME_OUT);
    }

    @Override
    protected void onResume() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        resumeUpdateIfNeeded();
        super.onResume();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUEST_CODE) {
            if (resultCode == RESULT_CANCELED) {
                log("Update flow RESULT_CANCELED " + resultCode);
                // If the update is cancelled or fails,
                // you can request to start the update again.
            } else if (resultCode == ActivityResult.RESULT_IN_APP_UPDATE_FAILED) {
                log("Update flow RESULT_IN_APP_UPDATE_FAILED " + resultCode);
            } else if (requestCode != RESULT_OK) {
                log("Update flow Failed " + resultCode);

            }
        }
    }

    private void checkForUpdates() {
        AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(this);
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();

        // Checks that the platform will allow the IMMEDIATE update.
        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE) {
                startUpdateFlow(appUpdateInfo);
            } else {
                startTheApp();
            }
        }).addOnFailureListener(e -> {
                    Log.e("appUpdateInfo", "onFailure: onResume" + e.getMessage());
                    startTheApp();
                }
        );
    }

    private void startUpdateFlow(AppUpdateInfo appUpdateInfo) {
        try {
            appUpdateManager.startUpdateFlowForResult(
                    appUpdateInfo,
                    AppUpdateType.IMMEDIATE,
                    this,
                    MY_REQUEST_CODE);
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    private void resumeUpdateIfNeeded() {
        appUpdateManager.getAppUpdateInfo().addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                try {
                    // If an in-app update is already running, resume the update.
                    appUpdateManager.startUpdateFlowForResult(
                            appUpdateInfo,
                            IMMEDIATE,
                            this,
                            MY_REQUEST_CODE);

                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void startTheApp() {
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
