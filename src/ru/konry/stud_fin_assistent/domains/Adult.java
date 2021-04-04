package ru.konry.stud_fin_assistent.domains;

import java.time.LocalDate;

public class Adult extends Person {

    private int passportSeries;
    private int passportNumber;
    private String issueDepartment;
    private LocalDate issueData;
    private String university;
    private String studentId;

    public Adult() {

    }

    public Adult(String name, String surname, String patronymic, LocalDate birthData, String university) {
        super(name, surname, patronymic, birthData);
        this.university = university;
    }

    public int getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(int passportSeries) {
        this.passportSeries = passportSeries;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getIssueDepartment() {
        return issueDepartment;
    }

    public void setIssueDepartment(String issueDepartment) {
        this.issueDepartment = issueDepartment;
    }

    public LocalDate getIssueData() {
        return issueData;
    }

    public void setIssueData(LocalDate issueData) {
        this.issueData = issueData;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}