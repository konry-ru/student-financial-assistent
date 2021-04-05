package ru.konry.stud_fin_assistent.domains;

import java.time.LocalDate;

public abstract class Person {

    protected String name;
    protected String surname;
    protected String patronymic;
    private Address adress = new Address();
    private LocalDate birthData;

    public Person() {

    }

    public Person(String name, String surname, String patronymic, LocalDate birthData) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthData = birthData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Address getAdress() {
        return adress;
    }

    public void setAdress(Address adress) {
        this.adress = adress;
    }

    public LocalDate getBirthData() {
        return birthData;
    }

    public void setBirthData(LocalDate birthData) {
        this.birthData = birthData;
    }
}
