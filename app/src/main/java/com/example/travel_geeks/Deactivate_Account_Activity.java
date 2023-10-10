package com.example.travel_geeks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Deactivate_Account_Activity extends AppCompatActivity {

    private Switch deactivateSwitch;
    private Button deactivateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deactivate_account);

        deactivateSwitch = findViewById(R.id.switchDeactivateAccount);
        deactivateButton = findViewById(R.id.btnDeactivate);

        deactivateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deactivateSwitch.isChecked()) {
                    deactivateAccount();
                } else {
                    Toast.makeText(Deactivate_Account_Activity.this, "Please enable the switch to deactivate your account.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void deactivateAccount() {
        // Get the user's ID or any other required information for deactivation
        String userId = "user_id";

        OkHttpClient client = new OkHttpClient();

        // Create a request to your API endpoint for account deactivation
        Request request = new Request.Builder()
                .url("YOUR_API_ENDPOINT_URL/user/deactivate/" + userId) //  API endpoint
                .post(new FormBody.Builder().build())
                .build();

        // Execute the request asynchronously
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Handle network error or API call failure
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Deactivate_Account_Activity.this, "Network error. Deactivation failed.", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    // Account deactivation successful
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Deactivate_Account_Activity.this, "Account deactivated successfully.", Toast.LENGTH_SHORT).show();
                            // You can navigate to another activity or perform other actions as needed
                            startActivity(new Intent(Deactivate_Account_Activity.this, Home_Activity.class));

                        }
                    });
                } else {
                    // Account deactivation failed
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Deactivate_Account_Activity.this, "Account deactivation failed. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
