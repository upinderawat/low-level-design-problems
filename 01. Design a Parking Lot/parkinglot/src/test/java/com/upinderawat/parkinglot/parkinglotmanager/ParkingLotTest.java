package com.upinderawat.parkinglot.parkinglotmanager;

import com.upinderawat.parkinglot.exceptions.NoParkingSpotAvailableException;
import com.upinderawat.parkinglot.parkingfloor.ParkingFloor;
import com.upinderawat.parkinglot.parkingmanager.ParkingLot;
import com.upinderawat.parkinglot.parkingspot.ParkingSpot;
import com.upinderawat.parkinglot.parkingspot.ParkingSpotType;
import com.upinderawat.parkinglot.vehicles.Bike;
import com.upinderawat.parkinglot.vehicles.Car;
import com.upinderawat.parkinglot.vehicles.Vehicle;
import com.upinderawat.parkinglot.vehicles.VehicleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

    private ParkingLot parkingLot;

    @BeforeEach
    void setUp() {
        parkingLot = new ParkingLot("TestParkingLot");
    }

    @Test
    void testAddParkingFloor() {
        ParkingFloor floor = new ParkingFloor("Floor-1", 5);
        parkingLot.addParkingFloor(floor);

        List<ParkingFloor> floors = parkingLot.getParkingFloors();
        assertEquals(1, floors.size(), "Parking floor should be added to the parking lot");
        assertEquals("Floor-1", floors.get(0).getParkingFloorId(), "Floor name should match the added floor");
    }

    @Test
    void testGetParkingFloors() {
        ParkingFloor floor1 = new ParkingFloor("Floor-1",5);
        ParkingFloor floor2 = new ParkingFloor("Floor-2",5);
        parkingLot.addParkingFloor(floor1);
        parkingLot.addParkingFloor(floor2);

        List<ParkingFloor> floors = parkingLot.getParkingFloors();
        assertEquals(2, floors.size(), "Parking lot should contain two floors");
    }

    @Test
    void testFindParkingSpotForVehicleSuccess() throws NoParkingSpotAvailableException {
        ParkingFloor floor = new ParkingFloor("Floor-1",5);
        ParkingSpot spot = new ParkingSpot("PS-1", ParkingSpotType.CAR);
        floor.addParkingSpot(spot);
        parkingLot.addParkingFloor(floor);

        Vehicle car = new Car("CAR-123");
        ParkingSpot foundSpot = parkingLot.findParkingSpotForVehicle(car);

        assertNotNull(foundSpot, "A parking spot should be found for the vehicle");
        assertEquals("PS-1", foundSpot.getParkingSpotId(), "The found spot ID should match the expected spot");
    }

    @Test
    void testFindParkingSpotForVehicleFailure() {
        ParkingFloor floor = new ParkingFloor("Floor-1",5);
        parkingLot.addParkingFloor(floor);

        Vehicle bike = new Bike("BIKE-123");

        assertThrows(NoParkingSpotAvailableException.class, () -> {
            parkingLot.findParkingSpotForVehicle(bike);
        }, "NoParkingSpotAvailableException should be thrown when no spot is available");
    }

    @Test
    void testGetParkingLotId() {
        assertEquals("TestParkingLot", parkingLot.getParkingLotId(), "Parking lot ID should match the initialized value");
    }
}