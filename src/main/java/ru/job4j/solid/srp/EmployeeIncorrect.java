package ru.job4j.solid.srp;

/**
 * Интерфейс EmployeeIncorrect, представленный здесь, описывает сущность сотрудник.
 * Этот класс нарушает принцип единственной ответственности (SRP).
 * В соответствии с принципом единственной ответственности класс должен решать лишь какую-то одну задачу.
 * Он же решает две: занимаясь работой с калькулированием продажи сотрудника calculateSalary
 * и подготовкой отчёта в методе generateReport.
 */

public interface EmployeeIncorrect {
    public void calculateSalary();

    public void generateReport();
}
