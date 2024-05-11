package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItDepartmentReportTest {
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
        Collections.sort(employeeList, new EmployeeAscByName());

        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        StringBuilder expected = new StringBuilder()
                .append("Name;Hired;Fired;Salary;")
                .append(System.lineSeparator());
        employeeList.forEach(emp -> expected
                .append(emp.getName()).append(";")
                .append(parser.parse(emp.getHired())).append(";")
                .append(parser.parse(emp.getFired())).append(";")
                .append(emp.getSalary()).append(";")
                .append(System.lineSeparator())
        );

        Report reportMaker = new ItDepartmentReport(store, parser);
        assertThat(reportMaker.generate(employee -> true)).isEqualTo(expected.toString());
    }

}