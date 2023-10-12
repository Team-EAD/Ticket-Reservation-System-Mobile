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

    private EditText reserveDateEditText;
    private EditText dateEditText;
    private EditText fromEditText;
    private EditText toEditText;
    private EditText ticketCountEditText;
    private EditText ticketPriceEditText;

    private Button reserveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reservation);

        // Initialize EditText fields and Button
        reserveDateEditText = findViewById(R.id.etReserveDate);
        dateEditText = findViewById(R.id.etDate);
        fromEditText = findViewById(R.id.etFromStation);
        toEditText = findViewById(R.id.etToStation);
        ticketCountEditText = findViewById(R.id.etNoOfTickets);
        ticketPriceEditText = findViewById(R.id.etPrice);

        reserveButton = findViewById(R.id.btnReserve);

        reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                String reserveDate = reserveDateEditText.getText().toString();
                String date = dateEditText.getText().toString();
                String from = fromEditText.getText().toString();
                String to = toEditText.getText().toString();
                String count = ticketCountEditText.getText().toString();
                String price = ticketPriceEditText.getText().toString();

                // Perform input validation here
                if (isValidInput(reserveDate, date, from, to, count, price)) {
                    // If input is valid, proceed with reservation
                    performReservation(reserveDate, date, from, to, count, price);
                } else {
                    // Display an error message for invalid input
                    Toast.makeText(Create_Reservation_Activity.this, "Invalid input. Please check your data.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidInput(String reserveDate, String date, String from, String to, String count, String price) {
        // Return true if input is valid, false otherwise
        boolean isValid = true;

        // Check if ticket type and date are not empty
        if (reserveDate.isEmpty() || date.isEmpty() || price.isEmpty() || from.isEmpty() || to.isEmpty()) {
            isValid = false;
        }

        return isValid;
    }

    private void performReservation( String reserveDate, String date, String from, String to, String count, String price) {
        OkHttpClient client = new OkHttpClient();

        // Prepare the request body with reservation data
        RequestBody requestBody = new FormBody.Builder()
                .add("reservationDate", reserveDate)
                .add("tripDate", date)
                .add("departureLocation", from)
                .add("departureLocation", to)
                .add("numberOfTickets", count)
                .add("price", price)
                .build();

        // backend API
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
