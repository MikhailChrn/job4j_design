package ru.job4j.ood.parking;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractParking {
    protected String title;

    protected int numberOfParkingSpace;

    protected List<AbstractVehicle> vehicles;

    protected AbstractParking(String title, int numberOfParkingSpace) {
        this.title = title;
        this.numberOfParkingSpace = numberOfParkingSpace;
        this.vehicles = new ArrayList<>();
    }

    protected void addVehicle(AbstractVehicle vehicle) throws Exception {
        if (vehicle.getNumberOfParkingSpaces() > this.numberOfParkingSpace) {
            throw new Exception("There are not enough parking spaces");
        }
        this.numberOfParkingSpace -= vehicle.getNumberOfParkingSpaces();
        this.vehicles.add(vehicle);
    }

    protected AbstractVehicle findVehicle(String vehicleNumber) throws Exception {
        AbstractVehicle result = null;
        for (AbstractVehicle vehicle : this.vehicles) {
            if (vehicleNumber.equals(vehicle.getVehicleNumber())) {
                result = vehicle;
                break;
            }
        }
        if (result == null) {
            throw new Exception("Vehicle not found");
        }
        return result;
    }

    protected int getNumberOfEmptySpace() {
        return numberOfParkingSpace;
    }

    public String getTitle() {
        return title;
    }
}
