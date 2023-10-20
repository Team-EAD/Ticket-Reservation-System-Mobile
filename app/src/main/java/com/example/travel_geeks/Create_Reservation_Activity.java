package com.example.travel_geeks;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class Create_Reservation_Activity extends AppCompatActivity {

    private EditText reserveDateEditText;
    private EditText dateEditText;
    private EditText fromEditText;
    private EditText toEditText;
    private EditText ticketCountEditText;
    private EditText ticketPriceEditText;

    private Button reserveButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reservation);
        databaseHelper = new DatabaseHelper(this);

        // Initialize EditText fields and Button
        reserveDateEditText = findViewById(R.id.etReserveDate);
        dateEditText = findViewById(R.id.etDate);
        fromEditText = findViewById(R.id.etFromStation);
        toEditText = findViewById(R.id.etToStation);
        ticketCountEditText = findViewById(R.id.etNoOfTickets);
        ticketPriceEditText = findViewById(R.id.etPrice);

        reserveButton = findViewById(R.id.btnReserve);

        reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //show datetime picker
                showDatePicker();

                //Count picker
                showNumberPicker();


                // Get user input
                String reserveDate = reserveDateEditText.getText().toString();
                String date = dateEditText.getText().toString();
                String from = fromEditText.getText().toString();
                String to = toEditText.getText().toString();
                String count = ticketCountEditText.getText().toString();
                String price = ticketPriceEditText.getText().toString();

                // Perform input validation here
                if (isValidInput(reserveDate, date, from, to, count, price)) {
                    // If input is valid, proceed with reservation
                    performReservation(reserveDate, date, from, to, count, price);
                } else {
                    // Display an error message for invalid input
                    Toast.makeText(Create_Reservation_Activity.this, "Invalid input. Please check your data.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Countpicker for tickets
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

        // Add 30 days to the current date
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        long maxDate = calendar.getTimeInMillis();

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Create a calendar for the selected date
                Calendar selectedCalendar = Calendar.getInstance();
                selectedCalendar.set(Calendar.YEAR, year);
                selectedCalendar.set(Calendar.MONTH, month);
                selectedCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                // Check if the selected date is within 30 days from now
                if (selectedCalendar.getTimeInMillis() > maxDate) {
                    // Display a toast message if the selected date is more than 30 days from today
                    Toast.makeText(getApplicationContext(), "Reservations can only be made within 30 days from today.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Set the selected date in the EditText
                String selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                reserveDateEditText.setText(selectedDate);
                dateEditText.setText(selectedDate);
            }
        }, year, month, day);

        // Set the minimum date to today
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis() - 30 * 24 * 60 * 60 * 1000L); // Subtract 30 days from the maximum date

        datePickerDialog.show();
    }


    private boolean isValidInput(String reserveDate, String date, String from, String to, String count, String price) {
        // Return true if input is valid, false otherwise
        boolean isValid = true;

        // Check if ticket type and date are not empty
        if (reserveDate.isEmpty() || date.isEmpty() || price.isEmpty() || from.isEmpty() || to.isEmpty()) {
            isValid = false;
        }

        return isValid;
    }

    private void performReservation( String reserveDate, String date, String from, String to, String count, String price) {
        databaseHelper.addReservation(reserveDate, date, from, to, count, price);

        Toast.makeText(Create_Reservation_Activity.this, "Reservation saved to local database!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Create_Reservation_Activity.this, View_Reservation_Activity.class));
    }
}
