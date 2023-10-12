package com.example.travel_geeks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Create_Account extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText nicEditText;
    private EditText CountryEditText;
    private EditText mobileEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button signUpButton;
    private TextView tvSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // Initialize EditText fields and Button
        firstNameEditText = findViewById(R.id.etFirstName);
        lastNameEditText = findViewById(R.id.etLastName);
        nicEditText = findViewById(R.id.etNIC);
        CountryEditText = findViewById(R.id.etCountry);
        mobileEditText = findViewById(R.id.etPhoneNumber);
        emailEditText = findViewById(R.id.etEmail);
        passwordEditText = findViewById(R.id.etPassword);

        signUpButton = findViewById(R.id.btnSignUp);
        tvSignIn = findViewById(R.id.tvSignIn);


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String nic = nicEditText.getText().toString();
                String country = CountryEditText.getText().toString();
                String mobile = mobileEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Perform input validation here
                if (firstName.isEmpty()) {
                    firstNameEditText.setError("First name is required");
                    return;
                }

                if (lastName.isEmpty()) {
                    lastNameEditText.setError("Last name is required");
                    return;
                }

                if (nic.isEmpty()) {
                    nicEditText.setError("NIC is required");
                    return;
                }

                if (country.isEmpty()) {
                    CountryEditText.setError("Country is required");
                    return;
                }

                if (mobile.isEmpty()) {
                    mobileEditText.setError("Mobile number is required");
                    return;
                }

                if (email.isEmpty()) {
                    emailEditText.setError("Email is required");
                    return;
                } else if (!isValidEmail(email)) {
                    emailEditText.setError("Invalid email format");
                    return;
                }

                if (password.isEmpty()) {
                    passwordEditText.setError("Password is required");
                    return;
                } else if (password.length() < 6) {
                    passwordEditText.setError("Password must be at least 6 characters");
                    return;
                }

                // Make API call for registration
                registerUser(firstName, lastName, nic, country, mobile, email, password);
            }

            // Email validation method
            private boolean isValidEmail(String email) {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                return email.matches(emailPattern);
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the login activity when "Sign In" is clicked
                Intent intent = new Intent(Create_Account.this, Login_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void registerUser(String firstName, String lastName, String nic, String country, String mobile, String email, String password) {
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("nic", nic)
                .add("country", country)
                .add("phone", mobile)
                .add("email", email)
                .add("password", password)
                .build();

        // Replace "YOUR_API_ENDPOINT_URL" with the actual URL of your backend API
        Request request = new Request.Builder()
                .url("https://localhost:44304/api/Traveler\n")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Handle registration failure
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Create_Account.this, "Account Creation Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Handle registration success or failure based on the API response
                if (response.isSuccessful()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Create_Account.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                            // Navigate to the next activity here
                                startActivity(new Intent(Create_Account.this, Home_Activity.class));
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Create_Account.this, "Account Creation Failed", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Create_Account.this, Login_Activity.class));
                        }
                    });
                }
            }
        });
    }
}
