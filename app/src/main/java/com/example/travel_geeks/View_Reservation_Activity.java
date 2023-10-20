package com.example.travel_geeks;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel_geeks.Adapter.ReservationAdapter;
import com.example.travel_geeks.DatabaseHelper.DatabaseHelper;
import com.example.travel_geeks.Model.Reservation;

import java.util.ArrayList;
import java.util.List;

public class View_Reservation_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ReservationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reservation);

        recyclerView = findViewById(R.id.recyclerViewReservations);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Retrieve reservations from the database
        List<Reservation> reservations = parseReservationsFromDatabase();

        // Set up RecyclerView adapter with the retrieved reservations
        adapter = new ReservationAdapter(reservations);
        recyclerView.setAdapter(adapter);
    }

    private List<Reservation> parseReservationsFromDatabase() {
        List<Reservation> reservations = new ArrayList<>();
        // Retrieve data from the SQLite database using DatabaseHelper
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        List<Reservation> reservationList = (List<Reservation>) databaseHelper.getAllReservations();
        for (Reservation reservation : reservationList) {
            // Add the retrieved Reservation objects to the list
            reservations.add(reservation);
        }
        return reservations;
    }
}
