package com.upinderawat.parkinglot.exceptions;

public class NoParkingSpotAvailableException extends Exception{
    public NoParkingSpotAvailableException() {
        super("No Parking Spot Available at the Floor");
    }

    public NoParkingSpotAvailableException(String message) {
        super(message);
    }
}
