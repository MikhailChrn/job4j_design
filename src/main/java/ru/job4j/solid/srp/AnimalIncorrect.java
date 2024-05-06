package ru.job4j.solid.srp;

/**
 * Интерфейс AnimalUncorrect, представленный здесь, описывает какое-то животное.
 * Этот класс нарушает принцип единственной ответственности (SRP).
 * В соответствии с принципом единственной ответственности класс должен решать лишь какую-то одну задачу.
 * Он же решает две: занимаясь работой с хранилищем данных в методе saveAnimal
 * и манипулируя свойствами объекта в конструкторе и в методе getAnimalName.
 */

public interface AnimalIncorrect {

    public AnimalIncorrect getAnimalName();

    public boolean saveAnimal(AnimalIncorrect animal);
}
