package ru.job4j.ood.parking;

import java.util.Objects;

public abstract class AbstractVehicle {
    protected String vehicleNumber;
    protected String carBrand;
    protected int numberOfParkingSpaces;

    public AbstractVehicle(String vehicleNumber, String carBrand) {
        this.vehicleNumber = vehicleNumber;
        this.carBrand = carBrand;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public int getNumberOfParkingSpaces() {
        return numberOfParkingSpaces;
    }

    public void setNumberOfParkingSpaces(int numberOfParkingSpaces) {
        this.numberOfParkingSpaces = numberOfParkingSpaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractVehicle that)) {
            return false;
        }
        return Objects.equals(vehicleNumber, that.vehicleNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(vehicleNumber);
    }
}
