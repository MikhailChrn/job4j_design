package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;

@XmlRootElement(name = "contact")
public class Contact {
    @XmlAttribute(name = "phone")
    private String phone;

    public Contact() { }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "phone='" + phone + '\''
                + '}';

    }
}