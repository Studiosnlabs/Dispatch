package com.example.dispatch.utils;

public class BatchUserFeed {
    String CourierName;
    String Location;
    String Destination;
    String Departure;
    String Arrival;
    String Phone;
    String SlotsLeft;

    public BatchUserFeed(String courierName, String location, String destination, String departure, String arrival, String phone, String slotsLeft) {
        CourierName = courierName;
        Location = location;
        Destination = destination;
        Departure = departure;
        Arrival = arrival;
        Phone = phone;
        SlotsLeft = slotsLeft;
    }

    public String getCourierName() {
        return CourierName;
    }

    public void setCourierName(String courierName) {
        CourierName = courierName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getDeparture() {
        return Departure;
    }

    public void setDeparture(String departure) {
        Departure = departure;
    }

    public String getArrival() {
        return Arrival;
    }

    public void setArrival(String arrival) {
        Arrival = arrival;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getSlotsLeft() {
        return SlotsLeft;
    }

    public void setSlotsLeft(String slotsLeft) {
        SlotsLeft = slotsLeft;
    }
}
