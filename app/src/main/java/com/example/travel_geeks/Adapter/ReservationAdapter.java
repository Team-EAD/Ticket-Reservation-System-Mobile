package com.example.travel_geeks.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel_geeks.Model.Reservation;
import com.example.travel_geeks.R;

import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder> {

    private List<Reservation> reservationList;

    public ReservationAdapter(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    @NonNull
    @Override
    public ReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_single_reservation, parent, false);
        return new ReservationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationViewHolder holder, int position) {
        Reservation reservation = reservationList.get(position);

        // Bind data to the view elements in the holder
        holder.ticketTypeTextView.setText(reservation.getTicketType());
        holder.dateTextView.setText(reservation.getDate());
        holder.timeTextView.setText(reservation.getTime());
        holder.fromTextView.setText(reservation.getFromStation());
        holder.toTextView.setText(reservation.getToStation());
        holder.nameTextView.setText(reservation.getTrainName());

    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }

    public class ReservationViewHolder extends RecyclerView.ViewHolder {
        TextView ticketTypeTextView;
        TextView dateTextView;
        TextView timeTextView;
        TextView fromTextView;
        TextView toTextView;
        TextView nameTextView;

        public ReservationViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize and bind the TextViews
            ticketTypeTextView = itemView.findViewById(R.id.textViewTicketType);
            dateTextView = itemView.findViewById(R.id.textViewDate);
            timeTextView = itemView.findViewById(R.id.textViewTime);
            fromTextView = itemView.findViewById(R.id.textViewFrom);
            toTextView = itemView.findViewById(R.id.textViewTo);
            nameTextView = itemView.findViewById(R.id.textViewName);

        }
    }
}
