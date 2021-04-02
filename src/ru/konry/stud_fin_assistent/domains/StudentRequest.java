package ru.konry.stud_fin_assistent.domains;

import ru.konry.stud_fin_assistent.domains.other.Adult;

public class StudentRequest {

    private Adult husband = new Adult();
    private Adult wife = new Adult();
    private Child child = new Child();

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