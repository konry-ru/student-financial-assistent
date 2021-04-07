package ru.konry.stud_fin_assistent.domains;

import java.time.LocalDate;
import java.util.ArrayList;

public class StudentRequest {

    private long studentRequestId;
    private Adult husband;
    private Adult wife;
    private ArrayList<Child> children;
    private String marriageCertificateId;
    private LocalDate marriageDate;
    private String marriageOffice;

    public long getStudentRequestId() {
        return studentRequestId;
    }

    public void setStudentRequestId(long studentRequestId) {
        this.studentRequestId = studentRequestId;
    }

    public Adult getHusband() {
        return husband;
    }

    public void setHusband(Adult husband) {
        this.husband = husband;
    }

    public Adult getWife() {
        return wife;
    }

    public void setWife(Adult wife) {
        this.wife = wife;
    }

    public ArrayList<Child> getChildren() {
        return children;
    }

    public void addChild(Child child) {
        if(this.children == null) {
            children = new ArrayList<>(5);
        }
        children.add(child);
    }

    public String getMarriageCertificateId() {
        return marriageCertificateId;
    }

    public void setMarriageCertificateId(String marriageCertificateId) {
        this.marriageCertificateId = marriageCertificateId;
    }

    public LocalDate getMarriageDate() {
        return marriageDate;
    }

    public void setMarriageDate(LocalDate marriageDate) {
        this.marriageDate = marriageDate;
    }

    public String getMarriageOffice() {
        return marriageOffice;
    }

    public void setMarriageOffice(String marriageOffice) {
        this.marriageOffice = marriageOffice;
    }

}