package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.EmployeeJsonXml;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class JsonReportEngine implements Report {
    private final Store store;
    private final Comparator<Employee> comparator;
    private final Gson gson;
    private final DateTimeParser<Calendar> parser;

    public JsonReportEngine(Store store, Comparator comparator) {
        this.parser = new ReportDateTimeParser();
        this.store = store;
        this.comparator = comparator;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return gson.toJson(store.findBy(filter).stream()
                .sorted(comparator)
                .map(emp -> new EmployeeJsonXml(
                        emp.getName(),
                        parser.parse(emp.getHired()),
                        parser.parse(emp.getFired()),
                        emp.getSalary()))
                .collect(Collectors.toList())
        );
    }
}
