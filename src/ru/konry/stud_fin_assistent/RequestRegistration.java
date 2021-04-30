package ru.konry.stud_fin_assistent;

import ru.konry.stud_fin_assistent.domains.*;

import java.time.LocalDate;

public class RequestRegistration {

    static StudentRequest createStudentRequest(long id) {
        StudentRequest sr = new StudentRequest();
        sr.setStudentRequestId(id);
        sr.setMarriageCertificateId("" + (12345600 + id));
        sr.setMarriageDate(LocalDate.of(2020, 7, 5));
        RegisterOffice marriageOffice = new RegisterOffice(1, "", "");
        sr.setMarriageOffice(marriageOffice);

        Address address = new Address("19500", new Street(1, ""), "12", "", "142");

        // Муж
        Adult husband = new Adult("Константин", "Корчагин", "Иванович", LocalDate.of(2000, 5, 24));
        husband.setPassportSeries("" + (1000 + id));
        husband.setPassportNumber("" + (100000 + id));
        husband.setIssueData(LocalDate.of(2016, 3, 12));
        PassportOffice po1 = new PassportOffice(1, "", "");
        husband.setIssueDepartment(po1);
        husband.setStudentId("" + (1000 + id));
        husband.setAdress(address);

        //Жена
        Adult wife = new Adult("Елена", "Васильевна", "Корчагина", LocalDate.of(2001, 8, 3));
        wife.setPassportSeries("" + (2000 + id));
        wife.setPassportNumber("" + (200000 + id));
        wife.setIssueData(LocalDate.of(2017, 4, 24));
        PassportOffice po2 = new PassportOffice(2, "", "");
        wife.setIssueDepartment(po2);
        wife.setStudentId("" + (2000 + id));
        wife.setAdress(address);

        //Ребенок 1
        Child child1 = new Child("Елизавета", "Константиновна", "Корчагина", LocalDate.of(2020, 1, 12));
        child1.setCertificateId("" + (300000 + id));
        child1.setIssueData(LocalDate.of(2020, 2, 11));
        RegisterOffice ro1 = new RegisterOffice(1, "", "");
        child1.setIssueDepartment(ro1);
        child1.setAddress(address);

        //Ребенок 1
        Child child2 = new Child("Артем", "Константинович", "Корчагин", LocalDate.of(2020, 1, 12));
        child2.setCertificateId("" + (400000 + id));
        child2.setIssueData(LocalDate.of(2020, 2, 11));
        RegisterOffice ro2 = new RegisterOffice(2, "", "");
        child2.setIssueDepartment(ro2);
        child2.setAddress(address);

        sr.setHusband(husband);
        sr.setWife(wife);
        sr.addChild(child1);
        sr.addChild(child2);

        return  sr;
    }
}
