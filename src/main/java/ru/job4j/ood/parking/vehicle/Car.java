package ru.job4j.ood.parking.vehicle;

import ru.job4j.ood.parking.AbstractVehicle;

public class Car extends AbstractVehicle {
    /**
     * Размер легкового автомобиля принимаем 1шт. машиноместо.
     */

    public Car(String vehicleNumber, String carBrand) {
        super(vehicleNumber, carBrand);
        super.setNumberOfParkingSpaces(1);
    }
}
