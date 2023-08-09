package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "task")
public class Task {
    @XmlAttribute(name = "description")
    private String description;

    @XmlAttribute(name = "priority")
    private int priority;

    public Task() { }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
