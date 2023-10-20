package com.example.travel_geeks;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travel_geeks.DatabaseHelper.DatabaseHelper;

public class Login_Activity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private ProgressBar loadingIndicator;
    private TextView signUpButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);

        usernameEditText = findViewById(R.id.etUsername);
        passwordEditText = findViewById(R.id.etPassword);
        loginButton = findViewById(R.id.btnLogin);
        signUpButton = findViewById(R.id.tvSignUp);
        loadingIndicator = findViewById(R.id.loadingIndicator);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                showLoadingIndicator(); // Show loading indicator before making the request

                // Perform login with the local database
                performLogin(username, password);
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the create account activity when "Sign Up" is clicked
                Intent intent = new Intent(Login_Activity.this, Create_Account.class);
                startActivity(intent);
            }
        });
    }

    private void performLogin(String username, String password) {
        boolean isLoginSuccessful = databaseHelper.checkUserCredentials(username, password);
        if (isLoginSuccessful) {
            hideLoadingIndicator();
            Toast.makeText(Login_Activity.this, "Login successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Login_Activity.this, Home_Activity.class));
        } else {
            hideLoadingIndicator();
            Toast.makeText(Login_Activity.this, "Login failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void showLoadingIndicator() {
        loadingIndicator.setVisibility(View.VISIBLE); // Show the loading indicator
    }

    private void hideLoadingIndicator() {
        loadingIndicator.setVisibility(View.INVISIBLE); // Hide the loading indicator
    }
}
