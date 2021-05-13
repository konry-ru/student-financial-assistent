package ru.konry.stud_fin_assistent.domains;

import java.time.LocalDate;

public abstract class Person {

    protected String surName;
    protected String givenName;
    protected String patronymic;
    private Address address;
    private LocalDate birthData;

    public Person() {

    }

    public Person(String surName, String givenName, String patronymic, LocalDate birthData) {
        this.surName = surName;
        this.givenName = givenName;
        this.patronymic = patronymic;
        this.birthData = birthData;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getBirthData() {
        return birthData;
    }

    public void setBirthData(LocalDate birthData) {
        this.birthData = birthData;
    }
}
