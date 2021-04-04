package ru.konry.stud_fin_assistent.validators;

import ru.konry.stud_fin_assistent.domains.*;
import ru.konry.stud_fin_assistent.exceptions.CityRegisterException;

public class CityRegistryValidator {
    public String hostName;
    public String login;
    private String password = "1234";

    private final CityRegisterChecker personChecker;

    public CityRegistryValidator() {
        this.personChecker = new FakeCityRegisterChecker();
    }

    public AnswerCityRegistry checkCityRegistry(StudentRequest sr) {

        try {
            CityRegisterCheckerResponse hans = personChecker.checkPerson(sr.getHusband());
        } catch (CityRegisterException e) {
            e.printStackTrace();
        }
        try {
            CityRegisterCheckerResponse wans = personChecker.checkPerson(sr.getWife());
        } catch (CityRegisterException e) {
            e.printStackTrace();
        }
        try {
            CityRegisterCheckerResponse cans = personChecker.checkPerson(sr.getChild());
        } catch (CityRegisterException e) {
            e.printStackTrace();
        }

        return new AnswerCityRegistry();
    }
}
