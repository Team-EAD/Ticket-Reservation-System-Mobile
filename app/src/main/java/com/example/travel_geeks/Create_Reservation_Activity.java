package com.example.travel_geeks;

import android.content.Intent;
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

public class Create_Reservation_Activity extends AppCompatActivity {

    // New EditText fields
    private EditText referenceIDEditText;
    private EditText reserveTimeEditText;
    private EditText reserveTrainNameEditText;
    private EditText reservationDateEditText;
    private EditText bookingDateEditText;
    private EditText numberOfTicketsEditText;
    private EditText departureLocationEditText;
    private EditText destinationEditText;

    private Button reserveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reservation);

        // Initialize new EditText fields
        referenceIDEditText = findViewById(R.id.referenceID);
        reserveTimeEditText = findViewById(R.id.reservetime);
        reserveTrainNameEditText = findViewById(R.id.reservetrainName);
        reservationDateEditText = findViewById(R.id.reservationDate);
        bookingDateEditText = findViewById(R.id.bookingDate);
        numberOfTicketsEditText = findViewById(R.id.numberOfTickets);
        departureLocationEditText = findViewById(R.id.departureLocation);
        destinationEditText = findViewById(R.id.destination);

        reserveButton = findViewById(R.id.btnReserve);

        reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                String referenceID = referenceIDEditText.getText().toString();
                String reserveTime = reserveTimeEditText.getText().toString();
                String reserveTrainName = reserveTrainNameEditText.getText().toString();
                String reservationDate = reservationDateEditText.getText().toString();
                String bookingDate = bookingDateEditText.getText().toString();
                String numberOfTickets = numberOfTicketsEditText.getText().toString();
                String departureLocation = departureLocationEditText.getText().toString();
                String destination = destinationEditText.getText().toString();

                // Perform input validation here
                if (isValidInput(referenceID, reserveTime, reserveTrainName, reservationDate, bookingDate, numberOfTickets, departureLocation, destination)) {
                    // If input is valid, proceed with reservation
                    performReservation(referenceID, reserveTime, reserveTrainName, reservationDate, bookingDate, numberOfTickets, departureLocation, destination);
                } else {
                    // Display an error message for invalid input
                    Toast.makeText(Create_Reservation_Activity.this, "Invalid input. Please check your data.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidInput(String referenceID, String reserveTime, String reserveTrainName, String reservationDate, String bookingDate, String numberOfTickets, String departureLocation, String destination) {
        // Return true if input is valid, false otherwise
        boolean isValid = true;

        // Check if all necessary fields are not empty
        if (referenceID.isEmpty() || reserveTime.isEmpty() || reserveTrainName.isEmpty() || reservationDate.isEmpty() || bookingDate.isEmpty() || numberOfTickets.isEmpty() || departureLocation.isEmpty() || destination.isEmpty()) {
            isValid = false;
        }

        return isValid;
    }

    private void performReservation(String referenceID, String reserveTime, String reserveTrainName, String reservationDate, String bookingDate, String numberOfTickets, String departureLocation, String destination) {
        OkHttpClient client = new OkHttpClient();

        // Prepare the request body with reservation data
        RequestBody requestBody = new FormBody.Builder()
                .add("referenceID", referenceID)
                .add("reserveTime", reserveTime)
                .add("reserveTrainName", reserveTrainName)
                .add("reservationDate", reservationDate)
                .add("bookingDate", bookingDate)
                .add("numberOfTickets", numberOfTickets)
                .add("departureLocation", departureLocation)
                .add("destination", destination)
                .build();

        // Backend API endpoint
        Request request = new Request.Builder()
                .url("https://localhost:44304/api/TicketReservation")
                .post(requestBody)
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
                        Toast.makeText(Create_Reservation_Activity.this, "Network error. Reservation failed.", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    // Reservation successful
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Create_Reservation_Activity.this, "Reservation successful!", Toast.LENGTH_SHORT).show();
                            // Navigate to another activity or perform other actions here.
                            startActivity(new Intent(Create_Reservation_Activity.this, View_Reservation_Activity.class));

                        }
                    });
                } else {
                    // Reservation failed
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Create_Reservation_Activity.this, "Reservation failed. Please try again.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Create_Reservation_Activity.this, Home_Activity.class));

                        }
                    });
                }
            }
        });
    }
}
