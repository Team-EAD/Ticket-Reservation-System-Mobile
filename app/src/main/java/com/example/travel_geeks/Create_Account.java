package com.example.travel_geeks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travel_geeks.DatabaseHelper.DatabaseHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Create_Account extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText nicEditText;
    private EditText countryEditText;
    private EditText mobileEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button signUpButton;
    private TextView tvSignIn;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        databaseHelper = new DatabaseHelper(this);

        // Initialize EditText fields and Button
        firstNameEditText = findViewById(R.id.etFirstName);
        lastNameEditText = findViewById(R.id.etLastName);
        nicEditText = findViewById(R.id.etNIC);
        countryEditText = findViewById(R.id.etCountry);
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
                String country = countryEditText.getText().toString();
                String mobile = mobileEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Perform input validation here
                if (firstName.isEmpty() || lastName.isEmpty() || nic.isEmpty() || country.isEmpty() || mobile.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Create_Account.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!isValidEmail(email)) {
                    emailEditText.setError("Invalid email format");
                    return;
                }

                //password insert validations
                new PasswordStrengthValidator();

                // Save user account data to the local database
                saveUserAccount(firstName, lastName, nic, country, mobile, email, password);
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

    // Email validation method
    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }

    // Save user account data to the local database
    private void saveUserAccount(String firstName, String lastName, String nic, String country, String mobile, String email, String password) {
        databaseHelper.addUserAccount(firstName, lastName, nic, country, mobile, email, password);
        Toast.makeText(Create_Account.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Create_Account.this, Home_Activity.class));
    }


    //password validations
    public class PasswordStrengthValidator {

        public boolean isStrongPassword(String password) {
            // Check if the password meets the criteria for a strong password
            return isLongEnough(password) && hasUppercaseLetter(password) && hasLowercaseLetter(password) && hasSpecialCharacter(password);
        }

        public boolean isLongEnough(String password) {
            return password.length() >= 8;
        }

        public boolean hasUppercaseLetter(String password) {
            return !password.equals(password.toLowerCase());
        }

        public boolean hasLowercaseLetter(String password) {
            return !password.equals(password.toUpperCase());
        }

        public boolean hasSpecialCharacter(String password) {
            Pattern pattern = Pattern.compile("[!@#$%^&*()_+=|<>?{}\\[\\]~-]");
            Matcher matcher = pattern.matcher(password);
            return matcher.find();
        }

        public String suggestPassword() {
            // Suggest a strong password
            return "SuggestedPassword123!";
        }
    }
}
