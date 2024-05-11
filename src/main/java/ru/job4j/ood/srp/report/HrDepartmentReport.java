package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class HrDepartmentReport implements Report {
    private MemoryStore store;
    List<Employee> employees;

    public HrDepartmentReport(MemoryStore store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        this.employees = store.findBy(filter);
        Collections.sort(employees, new EmployeeDescBySalary());
        StringBuilder sb = new StringBuilder()
                .append("Name Salary")
                .append(System.lineSeparator());
        employees.forEach(emp -> sb
                .append(emp.getName()).append(" ")
                .append(emp.getSalary())
                .append(System.lineSeparator())
        );
        return sb.toString();
    }
}
