package com.example.travel_geeks;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Create_Reservation_Activity extends AppCompatActivity {

    // New EditText fields
    private EditText referenceIDEditText;
    private EditText reserveTimeEditText;
    private EditText reserveTrainNameEditText;
    private EditText reservationDateEditText;
    private EditText bookingDateEditText;
    private EditText numberOfTicketsEditText;
    private EditText departureLocationEditText;
    private EditText destinationEditText;

    private Button reserveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reservation);

        showDatePicker();
        showDatePicker();

        List<String> locationList = new ArrayList<>();
        String rawData = "    Maradana\n" +
                "        Dematagoda\n" +
                "        Kelaniya\n" +
                "        Wanawasala\n" +
                "        Hunupitiya\n" +
                "        Enderamulla\n" +
                "        Horape\n" +
                "        Ragama\n" +
                "        Walpola\n" +
                "        Batuwatte\n" +
                "        Bulugahagoda\n" +
                "        Ganemulla\n" +
                "        Yagoda\n" +
                "        Gampaha\n" +
                "        Daraluwa\n" +
                "        Bemmulla\n" +
                "        Magelegoda\n" +
                "        Heendeniya\n" +
                "        Veyangoda\n" +
                "        Wandurawa\n" +
                "        Keenawala\n" +
                "        Pallewala\n" +
                "        Ganegoda\n" +
                "        Wijayarajadahana\n" +
                "        Mihirigama\n" +
                "        Wilwatte\n" +
                "        Botale\n" +
                "        Abeypussa\n" +
                "        Yattalgoda\n" +
                "        Buthgamuwa\n" +
                "        Alawwa\n" +
                "        Wlakubura\n" +
                "        Poplgahawela\n" +
                "        Panaleeya\n" +
                "        Tismalpola\n" +
                "        Yatagama\n" +
                "        Rambukkana\n" +
                "        Kadigamuwa\n" +
                "        Gangoda\n" +
                "        Ihalakotte\n" +
                "        Balana\n" +
                "        Kadugannawa\n" +
                "        Pilimatalawa\n" +
                "        Peradeniya\n" +
                "        Koshinna\n" +
                "        Gelioya\n" +
                "        Gampola\n" +
                "        Tembligala\n" +
                "        Ulapone\n" +
                "        Nawalapitiya\n" +
                "        Inguruoya\n" +
                "        Galaboda\n" +
                "        Watawala\n" +
                "        Ihalawatawala\n" +
                "        Rosella\n" +
                "        Hatton\n" +
                "        Kotagala\n" +
                "        Talawakele\n" +
                "        watagoda\n" +
                "        Greatwestern\n" +
                "        Radella\n" +
                "        Nanuoya\n" +
                "        Perakumpura\n" +
                "        Ambewela\n" +
                "        Pattipola\n" +
                "        Ohiya\n" +
                "        Idalgasinna\n" +
                "        Haputale\n" +
                "        Deyatalawa\n" +
                "        Bandarawela\n" +
                "        Kinigama\n" +
                "        Heeloya\n" +
                "        Kitalelle\n" +
                "        Elle\n" +
                "        Demodara\n" +
                "        Uduwara\n" +
                "        Haliela\n" +
                "        Badulla";

        String[] parts = rawData.split("\\s+");
        for (int i = 0; i < parts.length; i++) {
            if (i % 3 == 0) { // considering every third value as a location name
                locationList.add(parts[i]);
            }
        }

        // Populate the Spinner with locationList
        Spinner departureSpinner = findViewById(R.id.departureLocation);
        Spinner destinationSpinner = findViewById(R.id.destination);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locationList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        departureSpinner.setAdapter(adapter);
        destinationSpinner.setAdapter(adapter);

        departureSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLocation = parent.getItemAtPosition(position).toString();
                // Use the selected location as needed
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do something when nothing is selected
            }
        });

        destinationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLocation = parent.getItemAtPosition(position).toString();
                // Use the selected location as needed
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do something when nothing is selected
            }
        });

        // Initialize new EditText fields
        referenceIDEditText = findViewById(R.id.referenceID);
        reserveTimeEditText = findViewById(R.id.reservetime);
        reserveTrainNameEditText = findViewById(R.id.reservetrainName);
        reservationDateEditText = findViewById(R.id.reservationDate);
        bookingDateEditText = findViewById(R.id.bookingDate);
        numberOfTicketsEditText = findViewById(R.id.numberOfTickets);
        departureLocationEditText = findViewById(R.id.departureLocation);
        destinationEditText = findViewById(R.id.destination);

        reserveButton = findViewById(R.id.btnReserve);

        reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input
                String referenceID = referenceIDEditText.getText().toString();
                String reserveTime = reserveTimeEditText.getText().toString();
                String reserveTrainName = reserveTrainNameEditText.getText().toString();
                String reservationDate = reservationDateEditText.getText().toString();
                String bookingDate = bookingDateEditText.getText().toString();
                String numberOfTickets = numberOfTicketsEditText.getText().toString();
                String departureLocation = departureLocationEditText.getText().toString();
                String destination = destinationEditText.getText().toString();

                // Perform input validation here
                if (isValidInput(referenceID, reserveTime, reserveTrainName, reservationDate, bookingDate, numberOfTickets, departureLocation, destination)) {
                    // If input is valid, proceed with reservation
                    performReservation(referenceID, reserveTime, reserveTrainName, reservationDate, bookingDate, numberOfTickets, departureLocation, destination);
                } else {
                    // Display an error message for invalid input
                    Toast.makeText(Create_Reservation_Activity.this, "Invalid input. Please check your data.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

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
                numberOfTicketsEditText.setText(String.valueOf(numberPicker.getValue()));
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
                bookingDateEditText.setText(selectedDate);
                reservationDateEditText.setText(selectedDate);
            }
        }, year, month, day);

        // Set the minimum date to today
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis() - 30 * 24 * 60 * 60 * 1000L); // Subtract 30 days from the maximum date

        datePickerDialog.show();
    }


    private boolean isValidInput(String referenceID, String reserveTime, String reserveTrainName, String reservationDate, String bookingDate, String numberOfTickets, String departureLocation, String destination) {
        // Return true if input is valid, false otherwise
        boolean isValid = true;

        // Check if all necessary fields are not empty
        if (referenceID.isEmpty() || reserveTime.isEmpty() || reserveTrainName.isEmpty() || reservationDate.isEmpty() || bookingDate.isEmpty() || numberOfTickets.isEmpty() || departureLocation.isEmpty() || destination.isEmpty()) {
            isValid = false;
        }

        return isValid;
    }

    private void performReservation(String referenceID, String reserveTime, String reserveTrainName, String reservationDate, String bookingDate, String numberOfTickets, String departureLocation, String destination) {
        OkHttpClient client = new OkHttpClient();

        // Prepare the request body with reservation data
        RequestBody requestBody = new FormBody.Builder()
                .add("referenceID", referenceID)
                .add("reserveTime", reserveTime)
                .add("reserveTrainName", reserveTrainName)
                .add("reservationDate", reservationDate)
                .add("bookingDate", bookingDate)
                .add("numberOfTickets", numberOfTickets)
                .add("departureLocation", departureLocation)
                .add("destination", destination)
                .build();

        // Backend API endpoint
        Request request = new Request.Builder()
                .url("https://localhost:44304/api/TicketReservation")
                .post(requestBody)
                .build();

        // Execute the request asynchronously
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Handle network error or API call failure
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Create_Reservation_Activity.this, "Network error. Reservation failed.", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    // Reservation successful
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Create_Reservation_Activity.this, "Reservation successful!", Toast.LENGTH_SHORT).show();
                            // Navigate to another activity or perform other actions here.
                            startActivity(new Intent(Create_Reservation_Activity.this, View_Reservation_Activity.class));

                        }
                    });
                } else {
                    // Reservation failed
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Create_Reservation_Activity.this, "Reservation failed. Please try again.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Create_Reservation_Activity.this, Home_Activity.class));

                        }
                    });
                }
            }
        });
    }
}
