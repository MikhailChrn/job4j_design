package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "employee")
public class Employee {
    @XmlAttribute(name = "isManage")
    private boolean isManager;

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "position")
    private String position;

    @XmlAttribute(name = "age")
    private int age;

    @XmlElement(name = "contact")
    private Contact contact;

    @XmlElementWrapper(name = "tasks")
    @XmlElement(name = "task")
    private Task[] tasks;

    public Employee() { }

    @Override
    public String toString() {
        return "Employee{"
                + "name='" + name + '\''
                + ", contact=" + contact
                + ", tasks=" + Arrays.toString(tasks)
                + '}';
    }
}
