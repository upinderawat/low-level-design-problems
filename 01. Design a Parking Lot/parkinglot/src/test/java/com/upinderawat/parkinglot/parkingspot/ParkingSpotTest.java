package com.upinderawat.parkinglot.parkingspot;

import com.upinderawat.parkinglot.vehicles.Bike;
import com.upinderawat.parkinglot.vehicles.Car;
import com.upinderawat.parkinglot.vehicles.Vehicle;
import com.upinderawat.parkinglot.vehicles.VehicleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingSpotTest {

    private ParkingSpot parkingSpot;
    private Vehicle bike;
    private Vehicle car;

    @BeforeEach
    void setUp() {
        parkingSpot = new ParkingSpot("PS-1", ParkingSpotType.BIKE);
        bike = new Bike("BIKE-123");
        car = new Car("CAR-456");
    }

    @Test
    void testIsSpotAvailableInitially() {
        assertTrue(parkingSpot.isSpotAvailable());
    }

    @Test
    void testParkVehicleSuccess() {
        assertTrue(parkingSpot.parkVehicle(bike));
        assertFalse(parkingSpot.isSpotAvailable());
    }

    @Test
    void testParkVehicleFailureWhenSpotOccupied() {
        parkingSpot.parkVehicle(bike);
        assertFalse(parkingSpot.parkVehicle(car));
    }

    @Test
    void testParkVehicleFailureWhenVehicleDoesNotFit() {
        assertFalse(parkingSpot.parkVehicle(car));
    }

    @Test
    void testRemoveVehicle() {
        parkingSpot.parkVehicle(bike);
        parkingSpot.removeVehicle();
        assertTrue(parkingSpot.isSpotAvailable());
    }
}