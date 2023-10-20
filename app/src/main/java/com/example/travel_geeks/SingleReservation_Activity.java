package com.example.travel_geeks;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SingleReservation_Activity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_single_reservation);
//    }


    private TextView reservationDetailsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_reservation);

        reservationDetailsTextView = findViewById(R.id.textViewTicketType);

        // Example: Set some text in the TextView
        reservationDetailsTextView.setText("Reservation details will appear here.");
    }
}