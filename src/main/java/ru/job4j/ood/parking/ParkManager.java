package ru.job4j.ood.parking;

import java.util.ArrayList;
import java.util.List;

public class ParkManager {
    private final List<AbstractParking> parks = new ArrayList<>();

    public void addNewPark(AbstractParking park) {
        this.parks.add(park);
    }

    public AbstractParking findParking(String title) throws Exception {
        AbstractParking result = null;
        for (AbstractParking parking : this.parks) {
            if (title.equals(parking.getTitle())) {
                result = parking;
                break;
            }
        }
        if (result == null) {
            throw new Exception("Parking not found");
        }
        return result;
    }
}
