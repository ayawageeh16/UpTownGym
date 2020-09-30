package com.uptown.gym.trainee.view.registeration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.uptown.gym.trainee.R;
import com.uptown.gym.trainee.databinding.ActivityLoginBinding;
import com.uptown.gym.trainee.listener.LoginListener;
import com.uptown.gym.trainee.model.trainer.TrainerLoginDTO;
import com.uptown.gym.trainee.util.Constants;
import com.uptown.gym.trainee.util.NetworkConnection;
import com.uptown.gym.trainee.util.ViewUtils;
import com.uptown.gym.trainee.view.HomeActivity;
import com.uptown.gym.trainee.viewmodel.LoginViewModel;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;


public class LoginActivity extends AppCompatActivity implements LoginListener {


    private ActivityLoginBinding dataBinding;

    private LoginViewModel loginViewModel;
    private String loggedUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBar();
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        dataBinding.setListener(this);
        init();
    }

    private void init() {
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        handleTextInputLayoutError();
        FirebaseApp.initializeApp(this);
    }

    private void setActionBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
    }

    private void handleTextInputLayoutError() {
        Objects.requireNonNull(dataBinding.traineeNameLoginEditText.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!dataBinding.traineeNameLoginEditText.getEditText().getText().toString().isEmpty()) {
                    dataBinding.traineeNameLoginEditText.setError(null);
                }
            }
        });

        Objects.requireNonNull(dataBinding.traineePasswordLoginEditText.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!dataBinding.traineePasswordLoginEditText.getEditText().getText().toString().isEmpty()) {
                    dataBinding.traineePasswordLoginEditText.setError(null);
                }
            }
        });
    }

    private void getTraineeData() {
        String username = Objects.requireNonNull(dataBinding.traineeNameLoginEditText.getEditText()).getText().toString().trim();
        String password = Objects.requireNonNull(dataBinding.traineePasswordLoginEditText.getEditText()).getText().toString().trim();
        if (validateData(username, password)) {
            login(username, password);
        }
    }

    private boolean validateData(String username, String password) {
        boolean flag = true;

        // Validate user name
        if (username.isEmpty()) {
            dataBinding.traineeNameLoginEditText.setError(getString(R.string.error_required));
            flag = false;
        }

        // Validate password
        if (password.isEmpty()) {
            dataBinding.traineePasswordLoginEditText.setError(getString(R.string.error_required));
            flag = false;
        }

        return flag;
    }

    private void login(String username, String password) {
        if (NetworkConnection.isConnected(this)) {
            dataBinding.loginProgressBar.setVisibility(View.VISIBLE);
            ViewUtils.disableUI(this);
            TrainerLoginDTO trainer = new TrainerLoginDTO();
            trainer.setUsername(username);
            trainer.setPassword(password);
            loginViewModel.login(trainer).observe(this, trainerDTO -> {
                dataBinding.loginProgressBar.setVisibility(View.INVISIBLE);
                ViewUtils.enableUI(this);
                if (trainerDTO.getResponse() != null) {
                    // Save User Info In SharedPreferences
                    saveUserInfoInSharedPreferences(trainerDTO.getResponse().getId(), username, trainerDTO.getResponse().getGender());
                    Intent startHomeActivity = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(startHomeActivity);
                    clearData();
                    finish();

                } else {
                    dataBinding.loginProgressBar.setVisibility(View.INVISIBLE);
                    ViewUtils.showToast(this, trainerDTO.getMessage(), Toast.LENGTH_SHORT);
                }
            });
        } else {
            ViewUtils.showToast(this, getString(R.string.no_connection), Toast.LENGTH_SHORT);
        }
    }


    private void saveUserInfoInSharedPreferences(long userId, String userName, String gender) {
        String queryGender;
        switch (gender) {
            case "Female":
                queryGender = "FEMALE";
                break;
            default:
                queryGender = "MALE";
                break;
        }
        SharedPreferences settings = getSharedPreferences(Constants.User.USER_DATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Constants.User.USER_NAME, userName);
        editor.putString(Constants.User.GENDER, queryGender);
        editor.putLong(Constants.User.USER_ID, userId);
        editor.apply();
    }

    private void showSnakeBar(String message) {
        Snackbar snackbar = Snackbar.make(dataBinding.mainLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void clearData() {
        Objects.requireNonNull(dataBinding.traineeNameLoginEditText.getEditText()).setText("");
        Objects.requireNonNull(dataBinding.traineePasswordLoginEditText.getEditText()).setText("");
    }

    @Override
    public void onLoginClicked() {
        getTraineeData();
    }

}





