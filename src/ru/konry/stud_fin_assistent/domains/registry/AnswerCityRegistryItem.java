package ru.konry.stud_fin_assistent.domains.registry;

import ru.konry.stud_fin_assistent.domains.Person;

public class AnswerCityRegistryItem
{
    public enum CityStatus {
        YES, NO, ERROR;
    }

    public static class CityError {
        private String code;
        private String text;

        public CityError(String code, String text) {
            this.code = code;
            this.text = text;
        }

        public String getCode() {
            return code;
        }

        public String getText() {
            return text;
        }
    }

    private CityStatus status;
    private Person person;
    private CityError error;

    public AnswerCityRegistryItem(CityStatus status, Person person) {
        this.status = status;
        this.person = person;
    }

    public AnswerCityRegistryItem(CityStatus status, Person person, CityError error) {
        this.status = status;
        this.person = person;
        this.error = error;
    }

    public CityStatus getStatus() {
        return status;
    }

    public Person getPerson() {
        return person;
    }

    public CityError getError() {
        return error;
    }
}
