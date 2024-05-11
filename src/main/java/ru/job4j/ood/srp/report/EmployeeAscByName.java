package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;

import java.util.Comparator;

public class EmployeeAscByName implements Comparator<Employee> {
    @Override
    public int compare(Employee left, Employee right) {
        return left.getName().compareTo(right.getName());
    }
}
