package ru.job4j.ood.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.parking.vehicle.Car;
import ru.job4j.ood.parking.vehicle.Truck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Размер легкового автомобиля принимаем 1шт. машиноместо,
 * размер грузового 2шт. машиноместа;
 * Количество машиномест автомобиля задаётся в конструкторе классаж
 */

class SimpleParkTest {
    int sizeOfSimpleParking = 3;
    AbstractVehicle car;
    AbstractVehicle truck;

    {
        this.car = new Car("a123bc163ru", "KIA");
        this.truck = new Truck("d456ef163ru", "Isuzu");
    }

    @Test
    void calculateFreeSpaceIntoEmptyPark() {
        AbstractParking park = new SimplePark("first", this.sizeOfSimpleParking);
        assertEquals(this.sizeOfSimpleParking, park.getNumberOfEmptySpace());
    }

    @Test
    void whenThereAreNotEnoughParkSpace() throws Exception {
        AbstractParking park = new SimplePark("second", this.sizeOfSimpleParking);
        park.addVehicle(this.truck);
        Exception exception = assertThrows(
                Exception.class,
                () -> {
                    park.addVehicle(this.truck);
                });
        assertThat(exception.getMessage()).isEqualTo("There are not enough parking spaces");
    }

    @Test
    void calculateFreeSpaceBeforeAndAfterAddingCar() throws Exception {
        AbstractParking park = new SimplePark("third", this.sizeOfSimpleParking);
        assertEquals(this.sizeOfSimpleParking, park.getNumberOfEmptySpace());
        park.addVehicle(this.car);
        assertEquals(this.sizeOfSimpleParking - 1, park.getNumberOfEmptySpace());
    }

    @Test
    void calculateFreeSpaceBeforeAndAfterAddingTruck() throws Exception {
        AbstractParking park = new SimplePark("fourth", this.sizeOfSimpleParking);
        assertEquals(this.sizeOfSimpleParking, park.getNumberOfEmptySpace());
        park.addVehicle(this.truck);
        assertEquals(this.sizeOfSimpleParking - 2, park.getNumberOfEmptySpace());
    }

    @Test
    void whenFindExistVehicle() throws Exception {
        AbstractParking park = new SimplePark("fifth", this.sizeOfSimpleParking);
        park.addVehicle(this.truck);
        String brand = this.truck.getCarBrand();
        String number = this.truck.getVehicleNumber();
        String expected = park.findVehicle(number).getCarBrand();
        assertEquals(brand, expected);
    }

    @Test
    void whenCallingNonexistentVehicle() throws Exception {
        AbstractParking park = new SimplePark("sixth", this.sizeOfSimpleParking);
        park.addVehicle(this.truck);
        Exception exception = assertThrows(
                Exception.class,
                () -> {
                    park.findVehicle("w777xyz777ua");
                });
        assertThat(exception.getMessage()).isEqualTo("Vehicle not found");
    }
}