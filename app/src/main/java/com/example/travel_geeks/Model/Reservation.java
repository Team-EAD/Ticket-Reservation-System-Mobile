package com.example.travel_geeks.Model;

public class Reservation {
    private String referenceID;
    private String time;
    private String reservetrainName;
    private String reservetrainID;
    private String reservationDate;
    private String bookingDate;
    private int numberOfTickets;
    private String departureLocation;
    private String destination;

    // Constructors (you can have default and parameterized constructors)

    public Reservation() {
        // Default constructor
    }

    public Reservation(String referenceID, String time, String reservetrainName, String reservetrainID, String reservationDate, String bookingDate, int numberOfTickets, String departureLocation, String destination) {
        this.referenceID = referenceID;
        this.time = time;
        this.reservetrainName = reservetrainName;
        this.reservetrainID = reservetrainID;
        this.reservationDate = reservationDate;
        this.bookingDate = bookingDate;
        this.numberOfTickets = numberOfTickets;
        this.departureLocation = departureLocation;
        this.destination = destination;
    }

    // Getters and setters for each field

    public String getReferenceID() {
        return referenceID;
    }

    public void setReferenceID(String referenceID) {
        this.referenceID = referenceID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReservetrainName() {
        return reservetrainName;
    }

    public void setReservetrainName(String reservetrainName) {
        this.reservetrainName = reservetrainName;
    }

    public String getReservetrainID() {
        return reservetrainID;
    }

    public void setReservetrainID(String reservetrainID) {
        this.reservetrainID = reservetrainID;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
