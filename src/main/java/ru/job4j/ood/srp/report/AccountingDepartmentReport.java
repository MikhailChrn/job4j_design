package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class AccountingDepartmentReport implements Report {
    private MemoryStore store;
    private CurrencyConverter converter;
    private Currency target;
    List<Employee> employees;

    public AccountingDepartmentReport(MemoryStore store, CurrencyConverter converter, Currency target) {
        this.store = store;
        this.converter = converter;
        this.target = target;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        this.employees = store.findBy(filter);
        Collections.sort(employees, new EmployeeAscByName());
        StringBuilder sb = new StringBuilder()
                .append("Name Salary")
                .append(System.lineSeparator());
        employees.forEach(emp -> sb
                .append(emp.getName()).append(" ")
                .append(converter.convert(Currency.RUB, emp.getSalary(), target))
                .append(System.lineSeparator())
        );
        return sb.toString();
    }
}
