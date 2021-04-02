package ru.konry.stud_fin_assistent.domains;

public class Adress {
    private int postalCode;
    private String street;
    private int building;
    private int corpus;
    private int apartment;

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuilding() {
        return building;
    }

    public void setBuilding(int building) {
        this.building = building;
    }

    public int getCorpus() {
        return corpus;
    }

    public void setCorpus(int corpus) {
        this.corpus = corpus;
    }

    public int getApartment() {
        return apartment;
    }

    public void setApartment(int apartment) {
        this.apartment = apartment;
    }
}
