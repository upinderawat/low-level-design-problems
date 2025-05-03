package com.upinderawat.parkinglot.parkingfloor;

import com.upinderawat.parkinglot.parkingspot.ParkingSpot;
import com.upinderawat.parkinglot.parkingspot.ParkingSpotType;
import com.upinderawat.parkinglot.vehicles.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingFloor {
    private String parkingFloorId;
    private Integer maxLimit;

    private List<ParkingSpot> parkingSpots;

    public ParkingFloor(String parkingFloorId, Integer maxLimit) {
        this.parkingFloorId = parkingFloorId;
        this.maxLimit = maxLimit;
        this.parkingSpots = new ArrayList<>();
    }
    public Boolean addParkingSpot(ParkingSpot parkingSpot){
        if(this.parkingSpots.size() < maxLimit){
            this.parkingSpots.add(parkingSpot);
            return true;
        }
        else{
            return false;
        }
    }
    public ParkingSpot findAvailableSpots(ParkingSpotType vehicleType){
        Optional<ParkingSpot> availableParkingSpot = this.parkingSpots.stream().filter(x->x.isSpotAvailable() && x.getParkingSpotType().name().equals(vehicleType.name())).findFirst();
        return availableParkingSpot.orElse(null);
    }
    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }
    public String getParkingFloorId() {
        return parkingFloorId;
    }
}
