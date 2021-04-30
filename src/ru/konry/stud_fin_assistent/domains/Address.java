package ru.konry.stud_fin_assistent.domains;

public class Address {
    private String postalCode;
    private Street street;
    private String building;
    private String corpus;
    private String apartment;

    public Address() {

    }

    public Address(String postalCode, Street street, String building, String corpus, String apartment) {
        this.postalCode = postalCode;
        this.street = street;
        this.building = building;
        this.corpus = corpus;
        this.apartment = apartment;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getCorpus() {
        return corpus;
    }

    public void setCorpus(String corpus) {
        this.corpus = corpus;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }
}
