package com.upinderawat.parkinglot.ticket;

import com.upinderawat.parkinglot.parkingspot.ParkingSpot;
import com.upinderawat.parkinglot.vehicles.Vehicle;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    private String ticketId;
    private LocalDateTime inTime;
    private LocalDateTime outTime;
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;

    public Ticket(Vehicle vehicle, ParkingSpot parkingSpot) {
        this.ticketId = UUID.randomUUID().toString();
        this.inTime = LocalDateTime.now();
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
    }

    public void setOutTime(){
        this.outTime = LocalDateTime.now();
    }
    public String getTicketId() {
        return ticketId;
    }

    public LocalDateTime getInTime() {
        return inTime;
    }

    public LocalDateTime getOutTime() {
        return outTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }
}
