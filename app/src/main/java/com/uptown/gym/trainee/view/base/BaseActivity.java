package com.uptown.gym.trainee.view.base;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ProgressBar;
import com.uptown.gym.trainee.util.ViewUtils;

import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    /**
     * This method takes progressBar as a parameter and makes it Visible
     * And Disable Activity's UI components
     *
     * @param progressBar activity progressBar
     */
    protected void onRequestStart(ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
        ViewUtils.disableUI(this);
    }

    /**
     * This method takes progressBar as a parameter and makes it Invisible
     * And Enable Activity's UI components
     *
     * @param progressBar activity progressBar
     */
    protected void onRequestEnd(ProgressBar progressBar) {
        progressBar.setVisibility(View.INVISIBLE);
        ViewUtils.enableUI(this);
    }

    /**
     * This method  sets action bar title and enable home button
     *
     * @param title Action bar title
     */
    protected void setupActionBar(String title) {
        getSupportActionBar().setTitle(title); // for set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for add back arrow in action bar
    }

}
