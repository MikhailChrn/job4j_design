package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.EmployeeJsonXml;
import ru.job4j.ood.srp.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class XmlReportEngine implements Report {
    private final Store store;
    private final Comparator<Employee> comparator;
    private final DateTimeParser<Calendar> parser;
    private final JAXBContext context;
    private final Marshaller marshaller;

    public XmlReportEngine(Store store, Comparator comparator) throws JAXBException {
        this.parser = new ReportDateTimeParser();
        this.store = store;
        this.comparator = comparator;
        this.context = JAXBContext.newInstance(EmployeeJsonXml.class, ListEmployee.class);
        this.marshaller = context.createMarshaller();
        this.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<EmployeeJsonXml> employeeList = store.findBy(filter).stream()
                .sorted(comparator)
                .map(emp -> new EmployeeJsonXml(
                        emp.getName(),
                        parser.parse(emp.getHired()),
                        parser.parse(emp.getFired()),
                        emp.getSalary()))
                .collect(Collectors.toList());

        String xmlString = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new ListEmployee(employeeList), writer);
            xmlString = writer.getBuffer().toString();
        } catch (IOException | JAXBException ex) {
            ex.printStackTrace();
        }

        return xmlString;
    }

    @XmlRootElement(name = "employees")
    public static class ListEmployee {
        private List<EmployeeJsonXml> employee;

        public ListEmployee() {
        }

        public ListEmployee(List<EmployeeJsonXml> employee) {
            this.employee = employee;
        }

        public List<EmployeeJsonXml> getEmployee() {
            return employee;
        }

        public void setEmployee(List<EmployeeJsonXml> employee) {
            this.employee = employee;
        }
    }

}
