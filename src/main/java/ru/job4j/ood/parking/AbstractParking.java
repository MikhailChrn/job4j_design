package ru.job4j.ood.parking;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractParking {
    protected String title;

    protected int numberOfParkingSpace;

    protected List<AbstractVehicle> vehicles;

    protected abstract void addVehicle(AbstractVehicle vehicle);

    protected abstract AbstractVehicle findVehicle(String vehicleNumber);

    public AbstractParking(String title, int numberOfParkingSpace) {
        this.title = title;
        this.numberOfParkingSpace = numberOfParkingSpace;
        this.vehicles = new ArrayList<>();
    }

    protected int getNumberOfEmptySpace() {
        return 0;
    }
}
