package com.upinderawat.parkinglot;

import com.upinderawat.parkinglot.parkingfloor.ParkingFloor;
import com.upinderawat.parkinglot.parkingmanager.ParkingLot;
import com.upinderawat.parkinglot.parkingmanager.ParkingLotManager;
import com.upinderawat.parkinglot.parkingspot.ParkingSpot;
import com.upinderawat.parkinglot.parkingspot.ParkingSpotType;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParkinglotApplication {

    /*
        main should be able to
        create Parking Lot(ParkingFloors, ParkingSpots, ParkingSpotType)
     */
    public static void main(String[] args) {
        SpringApplication.run(ParkinglotApplication.class, args);
        ParkingLot parkingLot = new ParkingLot("VR mall Parking");

        ParkingFloor parkingFloorBasement2 = createParkingFloor("B2", 5, 
            new String[][] {
                {"B2-1", "BIKE"}, 
                {"B2-2", "BIKE"}, 
                {"B2-3", "CAR"}, 
                {"B2-4", "CAR"}, 
                {"B2-5", "TRUCK"}
            });

        ParkingFloor parkingFloorBasement1 = createParkingFloor("B1", 5, 
            new String[][] {
                {"B1-1", "BIKE"}, 
                {"B1-2", "CAR"}, 
                {"B1-3", "CAR"}, 
                {"B1-4", "TRUCK"}, 
                {"B1-5", "BIKE"}
            });

        ParkingFloor parkingFloorGround = createParkingFloor("G1", 5, 
            new String[][] {
                {"G1-1", "CAR"}, 
                {"G1-2", "CAR"}, 
                {"G1-3", "BIKE"}, 
                {"G1-4", "TRUCK"}, 
                {"G1-5", "BIKE"}
            });
		parkingLot.addParkingFloor(parkingFloorBasement2);
		parkingLot.addParkingFloor(parkingFloorBasement1);
		parkingLot.addParkingFloor(parkingFloorGround);

        ParkingLotManager parkingLotManager = new ParkingLotManager(parkingLot);
		System.out.println("Parking Lot Created: " + parkingLot.getParkingLotId());
		System.out.println("Parking Floors Created: " + parkingLot.getParkingFloors().size());
		
    }

    private static ParkingFloor createParkingFloor(String floorName, int capacity, String[][] parkingSpots) {
        ParkingFloor parkingFloor = new ParkingFloor(floorName, capacity);
        for (String[] spot : parkingSpots) {
            String spotId = spot[0];
            ParkingSpotType spotType = ParkingSpotType.valueOf(spot[1]);
            parkingFloor.addParkingSpot(new ParkingSpot(spotId, spotType));
        }
        return parkingFloor;
    }
}