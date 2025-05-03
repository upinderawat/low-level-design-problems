package com.upinderawat.parkinglot.parkingmanager;

import com.upinderawat.parkinglot.exceptions.NoParkingSpotAvailableException;
import com.upinderawat.parkinglot.parkingfloor.ParkingFloor;
import com.upinderawat.parkinglot.parkingspot.ParkingSpot;
import com.upinderawat.parkinglot.parkingspot.ParkingSpotType;
import com.upinderawat.parkinglot.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private String parkingLotId;
    private List<ParkingFloor> parkingFloors;

    public ParkingLot(String parkingLotId) {
        this.parkingLotId = parkingLotId;
        this.parkingFloors = new ArrayList<>();
    }

    public void addParkingFloor(ParkingFloor parkingFloor){
        this.parkingFloors.add(parkingFloor);
    }
    public ParkingSpot findParkingSpotForVehicle(Vehicle vehicle) throws NoParkingSpotAvailableException{
        ParkingSpot availableParkingSpot = null;
        for(ParkingFloor parkingFloor : this.parkingFloors){
            availableParkingSpot = parkingFloor.findAvailableSpots(ParkingSpotType.valueOf(vehicle.getType().name()));
            if(availableParkingSpot != null){
                break;
            }
        }
        if(availableParkingSpot == null){
            throw new NoParkingSpotAvailableException();
        }
        return availableParkingSpot;
    }
    public List<ParkingFloor> getParkingFloors(){
        return this.parkingFloors;
    }
    public String getParkingLotId() {
        return parkingLotId;
    }
}
