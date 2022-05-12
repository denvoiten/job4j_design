package ru.job4j.odd.report;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.function.Predicate;

public class ReportXML implements Report {
    private Store store;

    public ReportXML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String rsl = "";
        try (StringWriter writer = new StringWriter()) {
            StoreEmployee employees = new StoreEmployee(store.findBy(filter));
            JAXBContext context = JAXBContext.newInstance(StoreEmployee.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(employees, writer);
            rsl = writer.getBuffer().toString();
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }
}
