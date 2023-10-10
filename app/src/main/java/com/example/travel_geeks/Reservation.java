package com.example.travel_geeks;

public class Reservation {

    private String id;
    private String name;

    public Reservation(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
