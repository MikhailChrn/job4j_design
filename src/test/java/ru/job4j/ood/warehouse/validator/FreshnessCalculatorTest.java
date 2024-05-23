package ru.job4j.ood.warehouse.validator;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.warehouse.AbstractProduct;
import ru.job4j.ood.warehouse.Calculator;
import ru.job4j.ood.warehouse.calculator.FreshnessCalculator;
import ru.job4j.ood.warehouse.product.Bread;
import ru.job4j.ood.warehouse.product.Fruit;
import ru.job4j.ood.warehouse.product.Vegetable;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

class FreshnessCalculatorTest {
    LocalDate currentDate;
    AbstractProduct bread;
    AbstractProduct vegetable0;
    AbstractProduct vegetable1;
    AbstractProduct fruit;

    {
        this.currentDate = LocalDate.of(2024, Month.MAY, 20);

        this.bread =
                new Bread("Хлеб ржано-пшеничный Бородинский, 300 гр", currentDate);
        this.vegetable0 =
                new Vegetable("Морковь Эконом, весовой", currentDate.minusWeeks(2));
        this.vegetable1 =
                new Vegetable("Картофель красный, весовой", currentDate.minusWeeks(3));
        this.fruit =
                new Fruit("Яблоки Сезонные, весовой", currentDate.minusWeeks(2));
    }

    @Test
    void whenCalculatingDifferentProducts() {
        Calculator<AbstractProduct> calculator = new FreshnessCalculator(currentDate);
        assertThat(100).isEqualTo(calculator.calculate(bread));
        assertThat(50).isEqualTo(calculator.calculate(vegetable0));
        assertThat(25).isEqualTo(calculator.calculate(vegetable1));
        assertThat(0).isEqualTo(calculator.calculate(fruit));
    }
}