package com.example.travel_geeks.Model;

public class Reservation {
    private String ticketType;
    private String date;
    private String time;
    private String fromStation;
    private String toStation;
    private int numberOfTickets;
    private String trainName;

    // Constructors (you can have default and parameterized constructors)

    public Reservation() {
        // Default constructor
    }

    public Reservation(String ticketType, String date, String time, String fromStation, String toStation, int numberOfTickets, String trainName) {
        this.ticketType = ticketType;
        this.date = date;
        this.time = time;
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.numberOfTickets = numberOfTickets;
        this.trainName = trainName;
    }

    // Getters and setters for each field

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFromStation() {
        return fromStation;
    }

    public void setFromStation(String fromStation) {
        this.fromStation = fromStation;
    }

    public String getToStation() {
        return toStation;
    }

    public void setToStation(String toStation) {
        this.toStation = toStation;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }
}
