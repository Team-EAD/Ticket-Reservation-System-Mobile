package com.example.travel_geeks;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Edit_User_Account_Activity extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText nicEditText;
    private EditText countryEditText;
    private EditText mobileEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_account);

        // Initialize EditText fields and Button
        firstNameEditText = findViewById(R.id.etFirstName);
        lastNameEditText = findViewById(R.id.etLastName);
        nicEditText = findViewById(R.id.etNIC);
        countryEditText = findViewById(R.id.etCountry);
        mobileEditText = findViewById(R.id.etPhoneNumber);
        emailEditText = findViewById(R.id.etEmail);
        passwordEditText = findViewById(R.id.etPassword);

        updateButton = findViewById(R.id.btnUpdate);

        // Fetch the user's existing data and populate the EditText fields for editing

        // Example: Fetch user data and populate EditText fields
        String existingFirstName = "Heshan";
        String existingLastName = "Kotuwegedara";
        String existingNIC = "200034800327";
        String existingCountry = "Sri Lanka";
        String existingMobile = "0775137994";
        String existingEmail = "heshank@example.com";

        firstNameEditText.setText(existingFirstName);
        lastNameEditText.setText(existingLastName);
        nicEditText.setText(existingNIC);
        countryEditText.setText(existingCountry);
        mobileEditText.setText(existingMobile);
        emailEditText.setText(existingEmail);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                String updatedFirstName = firstNameEditText.getText().toString();
                String updatedLastName = lastNameEditText.getText().toString();
                String updatedNIC = nicEditText.getText().toString();
                String updatedCountry = countryEditText.getText().toString();
                String updatedMobile = mobileEditText.getText().toString();
                String updatedEmail = emailEditText.getText().toString();
                String updatedPassword = passwordEditText.getText().toString();

                // Validate user input
                if (updatedFirstName.isEmpty() || updatedLastName.isEmpty() || updatedNIC.isEmpty() ||
                        updatedCountry.isEmpty() || updatedMobile.isEmpty() || updatedEmail.isEmpty()) {
                    Toast.makeText(Edit_User_Account_Activity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(updatedEmail)) {
                    emailEditText.setError("Invalid email format");
                } else if (updatedPassword.length() < 6) {
                    passwordEditText.setError("Password must be at least 6 characters");
                } else {
                    // Make API call to update the user account
                    updateUserAccount(updatedFirstName, updatedLastName, updatedNIC, updatedCountry, updatedMobile, updatedEmail, updatedPassword);
                }
            }
        });
    }

    // Email validation method
    private boolean isValidEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
        return Pattern.compile(emailPattern).matcher(email).matches();
    }

    private void updateUserAccount(String firstName, String lastName, String nic, String country, String mobile, String email, String password) {
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("nic", nic)
                .add("country", country)
                .add("phone", mobile)
                .add("email", email)
                .add("password", password) // choose to send a new password for updating
                .build();

        // Replace "YOUR_API_ENDPOINT_URL" with the actual URL of your backend API
        Request request = new Request.Builder()
                .url("YOUR_API_ENDPOINT_URL")
                .put(formBody) // Use PUT request to update user data
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Handle update failure
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Edit_User_Account_Activity.this, "Update Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Handle update success or failure based on the API response
                if (response.isSuccessful()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Edit_User_Account_Activity.this, "Account Updated Successfully", Toast.LENGTH_SHORT).show();
                            // Navigate to the next activity or perform other actions as needed
                            startActivity(new Intent(Edit_User_Account_Activity.this, Home_Activity.class));
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Edit_User_Account_Activity.this, "Update Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}