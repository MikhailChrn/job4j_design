package ru.job4j.ood.parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ParkManagerTest {
    int sizeOfSimpleParking = 3;

    @Test
    void whenAddNewSimpleParking() {
        String title0 = "First";
        String title1 = "Second";

        ParkManager manager = new ParkManager();
        manager.addNewPark(new SimplePark(title0, sizeOfSimpleParking));
        manager.addNewPark(new SimplePark(title1, sizeOfSimpleParking));

        assertEquals(sizeOfSimpleParking,
                manager.findParking(title0).getNumberOfEmptySpace());
        assertEquals(sizeOfSimpleParking,
                manager.findParking(title1).getNumberOfEmptySpace());
    }

    @Test
    void whenCallingInvalidArgument() {
        ParkManager manager = new ParkManager();
        Exception exception = assertThrows(
                Exception.class,
                () -> {
                    manager.findParking("First");
                });
        assertThat(exception.getMessage()).isEqualTo("Invalid argument");
    }
}