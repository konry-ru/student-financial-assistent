package ru.konry.stud_fin_assistent.domains;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class StudentRequest {

    private long studentRequestId;
    private LocalDateTime timeOfRequest;
    private StateOfRequest stateOfRequest;
    private Adult husband;
    private Adult wife;
    private ArrayList<Child> children;
    private String marriageCertificateId;
    private LocalDate marriageDate;
    private RegisterOffice marriageOffice;

    public long getStudentRequestId() {
        return studentRequestId;
    }

    public void setStudentRequestId(long studentRequestId) {
        this.studentRequestId = studentRequestId;
    }


    public LocalDateTime getTimeOfRequest() {
        return timeOfRequest;
    }

    public void setTimeOfRequest(LocalDateTime timeOfRequest) {
        this.timeOfRequest = timeOfRequest;
    }

    public StateOfRequest getStateOfRequest() {
        return stateOfRequest;
    }

    public void setStateOfRequest(StateOfRequest stateOfRequest) {
        this.stateOfRequest = stateOfRequest;
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

    public RegisterOffice getMarriageOffice() {
        return marriageOffice;
    }

    public void setMarriageOffice(RegisterOffice marriageOffice) {
        this.marriageOffice = marriageOffice;
    }

}