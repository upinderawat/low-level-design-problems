package com.upinderawat.parkinglot.parkingmanager;

import com.upinderawat.parkinglot.exceptions.NoParkingSpotAvailableException;
import com.upinderawat.parkinglot.parkingmanager.ParkingLot;
import com.upinderawat.parkinglot.parkingspot.ParkingSpot;
import com.upinderawat.parkinglot.ticket.Ticket;
import com.upinderawat.parkinglot.vehicles.Vehicle;
import com.upinderawat.parkinglot.vehicles.VehicleType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/*
    manager is responsible for following tasks
    1. Assigns a new ParkingSpot to Vehicle, Create a ticket
    2. Unassign vehicle from a ParkingSpot, Mark exit to ticket

 */
public class ParkingLotManager {
    private ParkingLot parkingLot;
    private Map<String, Ticket> activeTickets;
    public ParkingLotManager(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        this.activeTickets = new HashMap<>();
    }
    public Ticket parkVehicle(Vehicle vehicle){
        try{
            ParkingSpot parkingSpot = this.parkingLot.findParkingSpotForVehicle(vehicle);
            Ticket ticket = new Ticket(vehicle, parkingSpot);
            activeTickets.put(ticket.getTicketId(), ticket);
            System.out.println("ParkingLotManager:: assigned "+ticket.getTicketId()+" to +"+vehicle.getLicenseNumber());
            return ticket;
        }
        catch (NoParkingSpotAvailableException e){
            return null;
        }
    }
    public void unparkVehicle(String ticketid){
        Ticket ticket = activeTickets.get(ticketid);
        if(ticket == null){//TODO: raise proper exception to main class
            System.out.println("Invalid Ticketid");
            return;
        }
        ticket.setOutTime();
        ParkingSpot parkingSpot = ticket.getParkingSpot();
        Vehicle vehicle = ticket.getVehicle();
        parkingSpot.removeVehicle();
        activeTickets.remove(ticketid);
        double fee = calculateFee(ticket.getInTime(), ticket.getOutTime(), vehicle.getType());
        System.out.println("ParkingLotManager::Please pay "+fee+" Rupees at Counter");
    }
    public double calculateFee(LocalDateTime inTime, LocalDateTime outTime, VehicleType vehicleType){
        double multiplier = 1;
        switch (vehicleType.name()){
            case "BIKE":
                multiplier = 0.5;
                break;
            case "CAR":
                multiplier = 1;
                break;
            case "TRUCK":
                multiplier = 1.5;
                break;
        }
        return Duration.between(inTime, outTime).toMinutes()*multiplier;
    }

}
