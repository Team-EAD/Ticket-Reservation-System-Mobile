package com.example.travel_geeks;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel_geeks.Adapter.ReservationAdapter;
import com.example.travel_geeks.Model.Reservation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class View_Reservation_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ReservationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reservation);

        recyclerView = findViewById(R.id.recyclerViewReservations);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Make an API request to fetch reservations
        fetchReservationsFromApi();
    }

    private void fetchReservationsFromApi() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://localhost:44304/api/TicketReservation")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Handle network error
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(View_Reservation_Activity.this, "Error While loading the data", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String jsonResponse = response.body().string();
                    // Parse JSON response and create a list of Reservation objects
                    final List<Reservation> reservations = parseReservationsFromJson(jsonResponse);

                    runOnUiThread(() -> {
                        // Update the RecyclerView adapter with the reservations
                        adapter = new ReservationAdapter(reservations);
                        recyclerView.setAdapter(adapter);
                    });
                } else {
                    runOnUiThread(() -> Toast.makeText(View_Reservation_Activity.this, "Error", Toast.LENGTH_SHORT).show());
                }
            }
        });
    }

    private List<Reservation> parseReservationsFromJson(String jsonResponse) {
        List<Reservation> reservations = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonResponse);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Reservation reservation = new Reservation();
                reservation.setReferenceID(jsonObject.getString("referenceID"));
                reservation.setTime(jsonObject.getString("time"));
                reservation.setReservetrainName(jsonObject.getString("reservetrainName"));
                reservation.setReservetrainID(jsonObject.getString("reservetrainID"));
                reservation.setReservationDate(jsonObject.getString("reservationDate"));
                reservation.setBookingDate(jsonObject.getString("bookingDate"));
                reservation.setNumberOfTickets(jsonObject.getInt("numberOfTickets"));
                reservation.setDepartureLocation(jsonObject.getString("departureLocation"));
                reservation.setDestination(jsonObject.getString("destination"));
                reservations.add(reservation);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return reservations;
    }
}