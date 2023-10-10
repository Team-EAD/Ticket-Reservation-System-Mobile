package com.example.travel_geeks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

    public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder> {

        private List<Reservation> reservations;

        public ReservationAdapter(List<Reservation> reservations) {
            this.reservations = reservations;
        }

        @NonNull
        @Override
        public ReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_single_reservation, parent, false);
            return new ReservationViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ReservationViewHolder holder, int position) {
            Reservation reservation = reservations.get(position);
            holder.bind(reservation);
        }

        @Override
        public int getItemCount() {
            return reservations.size();
        }

        static class ReservationViewHolder extends RecyclerView.ViewHolder {
            private final TextView reservationNameTextView;

            ReservationViewHolder(View itemView) {
                super(itemView);
                reservationNameTextView = itemView.findViewById(R.id.reservationNameTextView);
            }

            void bind(Reservation reservation) {
                reservationNameTextView.setText(reservation.getName());
            }
        }
    }
