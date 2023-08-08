package ru.job4j.serialization;

import ru.job4j.serialization.json.Employee;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

public class MainXmlExample {
    public static void main(String[] args) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Employee.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        Employee employee = (Employee) unmarshaller.unmarshal(
                new File("src/main/java/ru/job4j/serialization/employee.xml"));

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(employee, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
