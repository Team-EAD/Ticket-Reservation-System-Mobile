package com.example.travel_geeks;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class View_Reservation_Activity extends AppCompatActivity {
            @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_view_reservation);

            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            List<Reservation> reservations = ReservationAPI.getReservations();
            ReservationAdapter adapter = new ReservationAdapter(reservations);
            recyclerView.setAdapter(adapter);
        }
}