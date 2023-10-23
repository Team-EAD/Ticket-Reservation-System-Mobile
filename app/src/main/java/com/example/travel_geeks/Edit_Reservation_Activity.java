package com.example.travel_geeks;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Edit_Reservation_Activity extends AppCompatActivity {

    private EditText referenceIDEditText;
    private EditText reserveTimeEditText;
    private EditText reserveTrainNameEditText;
    private EditText reservationDateEditText;
    private EditText bookingDateEditText;
    private EditText numberOfTicketsEditText;
    private EditText departureLocationEditText;
    private EditText destinationEditText;

    private Button updateButton;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_reservation);

        // Initialize EditText fields and Buttons
        referenceIDEditText = findViewById(R.id.referenceID);
        reserveTimeEditText = findViewById(R.id.reservetime);
        reserveTrainNameEditText = findViewById(R.id.reservetrainName);
        reservationDateEditText = findViewById(R.id.reservationDate);
        bookingDateEditText = findViewById(R.id.bookingDate);
        numberOfTicketsEditText = findViewById(R.id.numberOfTickets);
        departureLocationEditText = findViewById(R.id.departureLocation);
        destinationEditText = findViewById(R.id.destination);


        updateButton = findViewById(R.id.btnUpdate);
        deleteButton = findViewById(R.id.btnDelete);

        fetchReservationData("referenceIDEditText");

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

    // Method to fetch reservation data based on ID
    private void fetchReservationData(String reservationID) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://localhost:44304/api/TicketReservation/" + reservationID)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Edit_Reservation_Activity.this, "Failed to fetch reservation data. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String jsonResponse = response.body().string();
                    // Update the UI with the fetched reservation data
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject jsonObject = new JSONObject(jsonResponse);
                                referenceIDEditText.setText(jsonObject.getString("referenceID"));
                                reserveTimeEditText.setText(jsonObject.getString("time"));
                                reserveTrainNameEditText.setText(jsonObject.getString("reservetrainName"));
                                reservationDateEditText.setText(jsonObject.getString("reservationDate"));
                                bookingDateEditText.setText(jsonObject.getString("bookingDate"));
                                numberOfTicketsEditText.setText(String.valueOf(jsonObject.getInt("numberOfTickets")));
                                departureLocationEditText.setText(jsonObject.getString("departureLocation"));
                                destinationEditText.setText(jsonObject.getString("destination"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Edit_Reservation_Activity.this, "Failed to fetch reservation data. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    // Method to update reservation
    private void updateReservation() {
        // Get values from EditText fields
        String referenceID = referenceIDEditText.getText().toString();
        String reserveTime = reserveTimeEditText.getText().toString();
        String reserveTrainName = reserveTrainNameEditText.getText().toString();
        String reservationDate = reservationDateEditText.getText().toString();
        String bookingDate = bookingDateEditText.getText().toString();
        String numberOfTickets = numberOfTicketsEditText.getText().toString();
        String departureLocation = departureLocationEditText.getText().toString();
        String destination = destinationEditText.getText().toString();

        // Create a JSON request body with updated reservation data
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

        // Make an API PUT request to update the reservation
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://localhost:44304/api/TicketReservation/" + referenceIDEditText)
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
                .url("https://localhost:44304/api/TicketReservation/" + referenceIDEditText)
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
