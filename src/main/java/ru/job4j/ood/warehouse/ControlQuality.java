package ru.job4j.ood.warehouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private LocalDate currentDate;
    private final List<AbstractStore> storeList;

    public ControlQuality(LocalDate currentDate) {
        this.currentDate = currentDate;
        this.storeList = new ArrayList<>();
    }

    public void addStore(AbstractStore store) {
        store.setCurrentDate(this.currentDate);
        this.storeList.add(store);
    }

    public void addFood(AbstractProduct product) {
        this.storeList.forEach(store -> store.add(product));
    }

    public List<AbstractStore> getStoreList() {
        return List.copyOf(storeList);
    }
}
