package com.upinderawat.parkinglot.parkingfloor;

import com.upinderawat.parkinglot.parkingspot.ParkingSpot;
import com.upinderawat.parkinglot.parkingspot.ParkingSpotType;
import com.upinderawat.parkinglot.vehicles.Bike;
import com.upinderawat.parkinglot.vehicles.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingFloorTest {

    private ParkingFloor parkingFloor;

    @BeforeEach
    void setUp() {
        parkingFloor = new ParkingFloor("Floor-1", 5);
        parkingFloor.addParkingSpot(new ParkingSpot("GF-1", ParkingSpotType.BIKE));
        parkingFloor.addParkingSpot(new ParkingSpot("GF-2", ParkingSpotType.CAR));
    }

    @Test
    void testGetFloorName() {
        assertEquals("Floor-1", parkingFloor.getParkingFloorId(), "Floor name should match the initialized value");
    }

    @Test
    void testAddParkingSpot() {
        ParkingSpot newSpot = new ParkingSpot("PS-3", ParkingSpotType.TRUCK);
        parkingFloor.addParkingSpot(newSpot);

        List<ParkingSpot> spots = parkingFloor.getParkingSpots();
        assertTrue(spots.contains(newSpot), "New parking spot should be added to the floor");
    }

    @Test
    void testGetParkingSpots() {
        List<ParkingSpot> spots = parkingFloor.getParkingSpots();
        assertEquals(2, spots.size(), "Initial number of parking spots should be 2");
    }

    @Test
    void testFindAvailableSpot() {
        ParkingSpot availableSpot = parkingFloor.findAvailableSpots(ParkingSpotType.BIKE);
        assertNotNull(availableSpot, "An available spot for BIKE should be found");
        assertEquals("GF-1", availableSpot.getParkingSpotId(), "The available spot ID should match");
    }

    @Test
    void testFindAvailableSpotWhenNoneAvailable() {
        Vehicle bike = new Bike("BIKE-123");
        parkingFloor.findAvailableSpots(ParkingSpotType.BIKE).parkVehicle(bike); // Occupy the bike spot
        ParkingSpot availableSpot = parkingFloor.findAvailableSpots(ParkingSpotType.BIKE);
        assertNull(availableSpot, "No available spot should be found for BIKE when all are occupied");
    }
}