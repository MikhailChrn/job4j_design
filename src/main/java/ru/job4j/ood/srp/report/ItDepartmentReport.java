package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class ItDepartmentReport implements Report {
    private MemoryStore store;
    private DateTimeParser<Calendar> parser;
    List<Employee> employees;

    public ItDepartmentReport(MemoryStore store, DateTimeParser<Calendar> parser) {
        this.store = store;
        this.parser = parser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        this.employees = store.findBy(filter);
        Collections.sort(employees, new EmployeeAscByName());
        StringBuilder sb = new StringBuilder()
                .append("Name;Hired;Fired;Salary;")
                .append(System.lineSeparator());
        employees.forEach(emp -> sb
                .append(emp.getName()).append(";")
                .append(parser.parse(emp.getHired())).append(";")
                .append(parser.parse(emp.getFired())).append(";")
                .append(emp.getSalary()).append(";")
                .append(System.lineSeparator())
        );
        return sb.toString();
    }
}
