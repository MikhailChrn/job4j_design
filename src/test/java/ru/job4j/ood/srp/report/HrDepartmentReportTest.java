package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HrDepartmentReportTest {
    @Test
    public void whenGetListOfAllEmployees() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();

        List<Employee> employeeList = Arrays.asList(
                new Employee("Ab", now, now, 400_000),
                new Employee("Cd", now, now, 300_000),
                new Employee("Ef", now, now, 200_000),
                new Employee("Gh", now, now, 100_000));
        employeeList.forEach(store::add);
        Collections.sort(employeeList, new EmployeeDescBySalary());

        StringBuilder expected = new StringBuilder()
                .append("Name Salary")
                .append(System.lineSeparator());
        employeeList.forEach(emp -> expected
                .append(emp.getName()).append(" ")
                .append(emp.getSalary())
                .append(System.lineSeparator())
        );

        Report reportMaker = new HrDepartmentReport(store);
        assertThat(reportMaker.generate(employee -> true)).isEqualTo(expected.toString());

    }

}