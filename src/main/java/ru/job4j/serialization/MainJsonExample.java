package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import ru.job4j.serialization.json.Employee;

public class MainJsonExample {

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
                        + "{\"description\":\"third\",\"priority\":3}"
                        + "]"
                        + "}";
        final Employee employeeMod = gson.fromJson(employeeJson, Employee.class);
        JSONObject jsonEmployeeObj = new JSONObject(employeeMod);
        System.out.println(jsonEmployeeObj);
        jsonEmployeeObj.put("position", "expert-developer");
        System.out.println(jsonEmployeeObj);
    }
}
