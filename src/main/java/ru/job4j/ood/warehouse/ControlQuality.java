package ru.job4j.ood.warehouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ControlQuality {
    private LocalDate currentDate;
    private final List<AbstractStore> storeList;

    public ControlQuality(LocalDate currentDate) {
        this.currentDate = currentDate;
        this.storeList = new ArrayList<>();
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
        this.storeList.stream()
                .forEach(store ->
                        store.setCurrentDate(this.currentDate));
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

    public void resort() {
        storeList.stream()
                .map(Store::clear)
                .flatMap(Collection::stream)
                .forEach(this::addFood);
    }
}
