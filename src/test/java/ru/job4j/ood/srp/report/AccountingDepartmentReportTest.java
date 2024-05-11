package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class AccountingDepartmentReportTest {
    private static final double RUB_TO_EUR_EXCHANGE_RATE = 0.0166;

    @Test
    public void whenSalaryOfAllEmployeesConvertFromRubToEur() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();

        List<Employee> employeeList = Arrays.asList(
                new Employee("Ab", now, now, 400_000),
                new Employee("Cd", now, now, 300_000),
                new Employee("Ef", now, now, 200_000),
                new Employee("Gh", now, now, 100_000));
        employeeList.forEach(store::add);
        Collections.sort(employeeList, new EmployeeAscByName());

        StringBuilder expected = new StringBuilder()
                .append("Name Salary")
                .append(System.lineSeparator());
        employeeList.forEach(emp -> expected
                .append(emp.getName()).append(" ")
                .append(emp.getSalary() * RUB_TO_EUR_EXCHANGE_RATE)
                .append(System.lineSeparator())
        );

        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Report reportMaker = new AccountingDepartmentReport(store, converter, Currency.EUR);
        assertThat(reportMaker.generate(employee -> true)).isEqualTo(expected.toString());
    }
}