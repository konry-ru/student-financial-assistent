package ru.konry.stud_fin_assistent.domains;

import java.time.LocalDate;

public class Adult extends Person {

    private String passportSeries;
    private String passportNumber;
    private PassportOffice issueDepartment;
    private LocalDate issueData;
    private University university;
    private String studentId;

    public Adult() {

    }

    public Adult(String name, String surname, String patronymic, LocalDate birthData) {
        super(name, surname, patronymic, birthData);
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public PassportOffice getIssueDepartment() {
        return issueDepartment;
    }

    public void setIssueDepartment(PassportOffice issueDepartment) {
        this.issueDepartment = issueDepartment;
    }

    public LocalDate getIssueData() {
        return issueData;
    }

    public void setIssueData(LocalDate issueData) {
        this.issueData = issueData;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
