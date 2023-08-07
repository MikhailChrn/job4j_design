package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.Period;

public class Main {
    public static void main(String[] args) {
        final Gson gson = new GsonBuilder().create();
        final String employeeJson =
                "{"
                        + "\"isManager\":false,"
                        + "\"name\":\"Mikhail\","
                        + "\"position\":\"lead-developer\","
                        + "\"age\":40,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7.777.777.77.77\""
                        + "},"
                        + "\"tasks\":"
                        + "["
                            + "{\"description\":\"first\",\"priority\":1},"
                            + "{\"description\":\"second\",\"priority\":2},"
                            + "{\"description\":\"thrid\",\"priority\":3}"
                        + "]"
                + "}";
        final Employee employeeMod = gson.fromJson(employeeJson, Employee.class);
        String employeeBeforeString = gson.toJson(employeeMod);

        final Employee employeeAfter = gson.fromJson(employeeBeforeString, Employee.class);
        System.out.println("Employee after deserialization:");
        System.out.println(employeeAfter);
    }
}
