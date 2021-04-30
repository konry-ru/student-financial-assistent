package ru.konry.stud_fin_assistent.domains;

import java.time.LocalDate;

public class Child extends Person {

    private String certificateId;
    private RegisterOffice issueDepartment;
    private LocalDate issueData;
    private Address address;

    public Child() {

    }
    public Child(String name, String surname, String patronymic, LocalDate birthData) {
        super(name, surname, patronymic, birthData);
    }

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    public RegisterOffice getIssueDepartment() {
        return issueDepartment;
    }

    public void setIssueDepartment(RegisterOffice issueDepartment) {
        this.issueDepartment = issueDepartment;
    }

    public LocalDate getIssueData() {
        return issueData;
    }

    public void setIssueData(LocalDate issueData) {
        this.issueData = issueData;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
