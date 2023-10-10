package com.example.travel_geeks;

import java.util.ArrayList;
import java.util.List;

public class ReservationAPI {
    private static List<Reservation> reservations = new ArrayList<>();

        // Simulated API endpoint to fetch reservations
        public static List<Reservation> getReservations() {
            return reservations;
        }

        // Simulated API endpoint to add a reservation
        public static void addReservation(Reservation reservation) {
            reservations.add(reservation);
        }
    }
