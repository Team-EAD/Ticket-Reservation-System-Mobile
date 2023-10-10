package com.example.travel_geeks;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Edit_Reservation_Activity extends AppCompatActivity {

    private EditText ticketTypeEditText;
    private EditText dateEditText;
    private EditText timeEditText;
    private EditText fromEditText;
    private EditText toEditText;
    private EditText ticketCountEditText;
    private EditText trainNameEditText;

    private Button updateButton;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_reservation);

        // Initialize EditText fields and Buttons
        ticketTypeEditText = findViewById(R.id.etTicketType);
        dateEditText = findViewById(R.id.etDate);
        timeEditText = findViewById(R.id.etTime);
        fromEditText = findViewById(R.id.etFromStation);
        toEditText = findViewById(R.id.etToStation);
        ticketCountEditText = findViewById(R.id.etNoOfTickets);
        trainNameEditText = findViewById(R.id.etTrainName);

        updateButton = findViewById(R.id.btnUpdate);
        deleteButton = findViewById(R.id.btnDelete);

        // Set a click listener for the update button
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle update reservation logic here
                updateReservation();
            }
        });

        // Set a click listener for the delete button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle delete reservation logic here
                deleteReservation();
            }
        });
    }

    // Method to update reservation
    private void updateReservation() {
        // Get values from EditText fields
        String ticketType = ticketTypeEditText.getText().toString();
        String date = dateEditText.getText().toString();
        String time = timeEditText.getText().toString();
        String from = fromEditText.getText().toString();
        String to = toEditText.getText().toString();
        String ticketCount = ticketCountEditText.getText().toString();
        String trainName = trainNameEditText.getText().toString();

        // Create a JSON request body with updated reservation data
        RequestBody requestBody = new FormBody.Builder()
                .add("ticket_type", ticketType)
                .add("date", date)
                .add("time", time)
                .add("from", from)
                .add("to", to)
                .add("ticket_count", ticketCount)
                .add("train_name", trainName)
                .build();

        // Make an API PUT request to update the reservation
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("YOUR_API_UPDATE_ENDPOINT_URL")
                .put(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Handle update failure
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Edit_Reservation_Activity.this, "Update failed. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    // Reservation updated successfully
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Edit_Reservation_Activity.this, "Reservation updated successfully", Toast.LENGTH_SHORT).show();
                            // Handle any further actions or navigate to another activity
                        }
                    });
                } else {
                    // Update failed
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Edit_Reservation_Activity.this, "Update failed. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    // Method to delete reservation
    private void deleteReservation() {
        // Perform delete reservation logic here

        // Make an API DELETE request to delete the reservation
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("YOUR_API_DELETE_ENDPOINT_URL")
                .delete()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Handle delete failure
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Edit_Reservation_Activity.this, "Delete failed. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    // Reservation deleted successfully
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Edit_Reservation_Activity.this, "Reservation deleted successfully", Toast.LENGTH_SHORT).show();
                            // Handle any further actions or navigate to another activity
                        }
                    });
                } else {
                    // Delete failed
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Edit_Reservation_Activity.this, "Delete failed. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
