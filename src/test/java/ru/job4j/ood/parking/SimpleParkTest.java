package ru.job4j.ood.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.parking.vehicle.Car;
import ru.job4j.ood.parking.vehicle.Truck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Disabled
class SimpleParkTest {
    int sizeOfSimpleParking = 3;
    AbstractVehicle car;
    AbstractVehicle truck;

    /**
     * Размер легкового автомобиля принимаем 1шт. машиноместо,
     * размер грузового 2шт. машиноместа;
     * Количество машиномест автомобиля задаётся в конструкторе классаж
     */

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
    void calculateFreeSpaceBeforeAndAfterAddingCar() {
        AbstractParking park = new SimplePark("second", this.sizeOfSimpleParking);
        assertEquals(this.sizeOfSimpleParking, park.getNumberOfEmptySpace());
        park.addVehicle(car);
        assertEquals(this.sizeOfSimpleParking - 1, park.getNumberOfEmptySpace());
    }

    @Test
    void calculateFreeSpaceBeforeAndAfterAddingTruck() {
        AbstractParking park = new SimplePark("third", this.sizeOfSimpleParking);
        assertEquals(this.sizeOfSimpleParking, park.getNumberOfEmptySpace());
        park.addVehicle(truck);
        assertEquals(this.sizeOfSimpleParking - 2, park.getNumberOfEmptySpace());
    }

    @Test
    void whenCallingInvalidArgument() {
        AbstractParking park = new SimplePark("fourth", this.sizeOfSimpleParking);
        Exception exception = assertThrows(
                Exception.class,
                () -> {
                    park.findVehicle("w777xyz777ua");
                });
        assertThat(exception.getMessage()).isEqualTo("Vehicle not found");
    }
}