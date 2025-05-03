package com.upinderawat.parkinglot;

import com.upinderawat.parkinglot.exceptions.NoParkingSpotAvailableException;
import com.upinderawat.parkinglot.parkingmanager.ParkingLot;
import com.upinderawat.parkinglot.parkingmanager.ParkingLotManager;
import com.upinderawat.parkinglot.parkingspot.ParkingSpot;
import com.upinderawat.parkinglot.parkingspot.ParkingSpotType;
import com.upinderawat.parkinglot.ticket.Ticket;
import com.upinderawat.parkinglot.vehicles.Bike;
import com.upinderawat.parkinglot.vehicles.Vehicle;
import com.upinderawat.parkinglot.vehicles.VehicleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParkingLotManagerTest {

    private ParkingLotManager parkingLotManager;
    private ParkingLot parkingLot;
    private Vehicle bike;

    @BeforeEach
    void setUp() {
        parkingLot = mock(ParkingLot.class);
        parkingLotManager = new ParkingLotManager(parkingLot);
        bike = new Bike("BIKE-123");
    }

    @Test
    void testParkVehicleSuccess() throws NoParkingSpotAvailableException {
        ParkingSpot parkingSpot = new ParkingSpot("PS-1", ParkingSpotType.BIKE);
        when(parkingLot.findParkingSpotForVehicle(bike)).thenReturn(parkingSpot);

        Ticket ticket = parkingLotManager.parkVehicle(bike);

        assertNotNull(ticket);
        assertEquals(bike, ticket.getVehicle());
        verify(parkingLot, times(1)).findParkingSpotForVehicle(bike);
    }

    @Test
    void testParkVehicleFailure() throws NoParkingSpotAvailableException {
        when(parkingLot.findParkingSpotForVehicle(bike)).thenThrow(new NoParkingSpotAvailableException());

        Ticket ticket = parkingLotManager.parkVehicle(bike);

        assertNull(ticket);
        verify(parkingLot, times(1)).findParkingSpotForVehicle(bike);
    }

    @Test
    void testUnparkVehicle() {
        ParkingSpot parkingSpot = new ParkingSpot("PS-1", ParkingSpotType.BIKE);
        Ticket ticket = new Ticket(bike, parkingSpot);
        parkingLotManager.parkVehicle(bike);

        parkingLotManager.unparkVehicle(ticket.getTicketId());

        assertTrue(parkingSpot.isSpotAvailable());
    }

    @Test
    void testCalculateFee() {
        LocalDateTime inTime = LocalDateTime.now().minusHours(2);
        LocalDateTime outTime = LocalDateTime.now();

        double fee = parkingLotManager.calculateFee(inTime, outTime, VehicleType.BIKE);

        assertEquals(60.0, fee); // Assuming 0.5 multiplier for bikes
    }
}