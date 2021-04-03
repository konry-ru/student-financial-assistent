package ru.konry.stud_fin_assistent.domains;

public class StudentRequest {

    private long studentRequestId;
    private Adult husband = new Adult();
    private Adult wife = new Adult();
    private Child child = new Child();


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

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }
}