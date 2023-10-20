package com.example.travel_geeks;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travel_geeks.DatabaseHelper.DatabaseHelper;

import java.util.Calendar;

public class Edit_Reservation_Activity extends AppCompatActivity {

    private EditText reserveDateEditText;
    private EditText dateEditText;
    private EditText fromEditText;
    private EditText toEditText;
    private EditText ticketCountEditText;
    private EditText ticketPriceEditText;

    private Button updateButton;
    private Button deleteButton;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_reservation);

        databaseHelper = new DatabaseHelper(this);

        // Initialize EditText fields and Buttons
        reserveDateEditText = findViewById(R.id.etReserveDate);
        dateEditText = findViewById(R.id.etDate);
        fromEditText = findViewById(R.id.etFromStation);
        toEditText = findViewById(R.id.etToStation);
        ticketCountEditText = findViewById(R.id.etNoOfTickets);
        ticketPriceEditText = findViewById(R.id.etPrice);

        updateButton = findViewById(R.id.btnUpdate);
        deleteButton = findViewById(R.id.btnDelete);

        // Set a click listener for the update button
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle update reservation logic here
                showNumberPicker();
                showDatePicker();
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

    // Method to update reservation
    private void updateReservation() {
        // Get values from EditText fields
        String reserveDate = reserveDateEditText.getText().toString();
        String date = dateEditText.getText().toString();
        String from = fromEditText.getText().toString();
        String to = toEditText.getText().toString();
        String ticketCount = ticketCountEditText.getText().toString();
        String ticketPrice = ticketPriceEditText.getText().toString();

        // Update the reservation in the database
        databaseHelper.updateReservation(1, reserveDate, date, from, to, ticketCount, ticketPrice);

        // Display a toast message for successful update
        Toast.makeText(this, "Reservation updated successfully", Toast.LENGTH_SHORT).show();

        // Handle any further actions or navigate to another activity
    }

    // Method to delete reservation
    private void deleteReservation() {
        // Delete the reservation from the database
        // Replace the parameter with the actual reservation ID to delete
        databaseHelper.deleteReservation(1);

        // Display a toast message for successful deletion
        Toast.makeText(this, "Reservation deleted successfully", Toast.LENGTH_SHORT).show();

    }

    //Other methods
    //Count-picker for tickets
    private void showNumberPicker() {
        final NumberPicker numberPicker = new NumberPicker(this);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(100);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(numberPicker);
        builder.setTitle("Select Number of Tickets");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ticketCountEditText.setText(String.valueOf(numberPicker.getValue()));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    //datetime picker method
    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


                // Set the selected date in the EditText
                String selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                reserveDateEditText.setText(selectedDate);
                dateEditText.setText(selectedDate);

            }
        }, year, month, day);

        // Set the minimum date to today
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());

        datePickerDialog.show();
    }
}