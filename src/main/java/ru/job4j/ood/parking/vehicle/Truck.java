package ru.job4j.ood.parking.vehicle;

import ru.job4j.ood.parking.AbstractVehicle;

public class Truck extends AbstractVehicle {
    /**
     * Размер грузового автомобиля принимаем 2шт. машиноместо.
     */

    public Truck(String vehicleNumber, String carBrand) {
        super(vehicleNumber, carBrand);
        super.setNumberOfParkingSpaces(2);
    }
}
