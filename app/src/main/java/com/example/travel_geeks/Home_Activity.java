package com.example.travel_geeks;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.travel_geeks.Fragment.DeactivateAccountFragment;
import com.example.travel_geeks.Fragment.EditReservationFragment;
import com.example.travel_geeks.Fragment.MakeReservationFragment;
import com.example.travel_geeks.Fragment.ViewReservationFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home_Activity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;
    private Button btnCreateReservation, btnEditReservation, btnViewReservations, btnDeactivate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigation = findViewById(R.id.bottom_Navigation);
        btnCreateReservation = findViewById(R.id.btnCreateReservation);
        btnEditReservation = findViewById(R.id.btnEditReservation);
        btnViewReservations = findViewById(R.id.btnViewReservations);
        btnDeactivate = findViewById(R.id.btnDeactivate);

        // Set up the BottomNavigationView for fragment navigation
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.action_make_reservation) {
                    loadFragment(new MakeReservationFragment());
                    return true;
                } else if (itemId == R.id.action_update_reservation) {
                    loadFragment(new EditReservationFragment());
                    return true;
                } else if (itemId == R.id.action_view_reservation) {
                    loadFragment(new ViewReservationFragment());
                    return true;
                } else if (itemId == R.id.action_deactivate_account) {
                    loadFragment(new DeactivateAccountFragment());
                    return true;
                }

                return false;
            }
        });

                // Set up click listeners for the buttons to navigate to other activities
        btnCreateReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home_Activity.this, Create_Reservation_Activity.class));
            }
        });

        btnEditReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home_Activity.this, Edit_Reservation_Activity.class));
            }
        });

        btnViewReservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home_Activity.this, View_Reservation_Activity.class));
            }
        });

        btnDeactivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home_Activity.this, Deactivate_Account_Activity.class));
            }
        });
    }

    private void loadFragment(MakeReservationFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }

    private void loadFragment(EditReservationFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }

    private void loadFragment(ViewReservationFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }

    private void loadFragment(DeactivateAccountFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }
}
