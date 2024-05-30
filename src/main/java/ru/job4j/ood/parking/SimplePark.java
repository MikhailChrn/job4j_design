package ru.job4j.ood.parking;

public class SimplePark extends AbstractParking {
    public SimplePark(String title, int sizeOfParking) {
        super(title, sizeOfParking);
    }

    @Override
    protected void addVehicle(AbstractVehicle vehicle) {

    }

    @Override
    protected AbstractVehicle findVehicle(String vehicleNumber) {
        return null;
    }
}
