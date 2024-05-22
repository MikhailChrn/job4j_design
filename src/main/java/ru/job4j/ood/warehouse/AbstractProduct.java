package ru.job4j.ood.warehouse;

import java.time.LocalDate;
import java.util.Objects;

public abstract class AbstractProduct {
    private String title;
    private LocalDate expiryDate;
    private LocalDate createDate;
    private double price;
    private double discount;

    public AbstractProduct(String title, LocalDate createDate) {
        this.title = title;
        this.createDate = createDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public double getPrice() {
        return price - discount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AbstractProduct that)) {
            return false;
        }
        return Objects.equals(getTitle(), that.getTitle())
                && Objects.equals(getCreateDate(), that.getCreateDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getCreateDate());
    }
}
