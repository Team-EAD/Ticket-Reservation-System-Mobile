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

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Login_Activity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private ProgressBar loadingIndicator;
    private TextView signUpButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.etUsername);
        passwordEditText = findViewById(R.id.etPassword);
        loginButton = findViewById(R.id.btnLogin);
        signUpButton =  findViewById(R.id.tvSignUp);
        loadingIndicator = findViewById(R.id.loadingIndicator);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                showLoadingIndicator(); // Show loading indicator before making the request

                performLogin(username, password);
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the login activity when "Sign In" is clicked
                Intent intent = new Intent(Login_Activity.this, Create_Account.class);
                startActivity(intent);
            }
        });
    }

    private void performLogin(String username, String password) {
        OkHttpClient client = new OkHttpClient();

        FormBody formBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .build();

        Request request = new Request.Builder()
                .url("https://localhost:44304/api/auth/login")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideLoadingIndicator();
                        Toast.makeText(Login_Activity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideLoadingIndicator();
                            Toast.makeText(Login_Activity.this, "Login successful", Toast.LENGTH_SHORT).show();
                            // Navigate to the next activity here
                            startActivity(new Intent(Login_Activity.this, Home_Activity.class));

                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideLoadingIndicator();
                            Toast.makeText(Login_Activity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void showLoadingIndicator() {
        loadingIndicator.setVisibility(View.VISIBLE); // Show the loading indicator
    }

    private void hideLoadingIndicator() {
        loadingIndicator.setVisibility(View.INVISIBLE); // Hide the loading indicator
    }
}
