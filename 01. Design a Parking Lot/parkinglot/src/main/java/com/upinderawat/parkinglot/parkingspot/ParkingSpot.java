package com.upinderawat.parkinglot.parkingspot;

import com.upinderawat.parkinglot.vehicles.Vehicle;


public class ParkingSpot {
    private String parkingSpotId;
    private ParkingSpotType parkingSpotType;
    private Boolean isAvailable;
    private Vehicle currentVehicle;

    public ParkingSpot(String parkingSpotId, ParkingSpotType parkingSpotType) {
        this.parkingSpotId = parkingSpotId;
        this.parkingSpotType = parkingSpotType;
        this.isAvailable = true;
    }
    public Boolean isSpotAvailable(){
        return this.isAvailable;
    }
    public void removeVehicle(){
        this.currentVehicle = null;
        this.isAvailable = true;
    }
    public Boolean canFit(Vehicle vehicle){
        return vehicle.getType().name().equals(this.parkingSpotType.name());
    }
    public Boolean parkVehicle(Vehicle vehicle){
        if(!isAvailable || !canFit(vehicle)){
            return false;
        }
        else{
            this.currentVehicle = vehicle;
            this.isAvailable = false;
        }
        return true;
    }
    public ParkingSpotType getParkingSpotType() {
        return parkingSpotType;
    }

    public String getParkingSpotId() {
        return parkingSpotId;
    }
}
