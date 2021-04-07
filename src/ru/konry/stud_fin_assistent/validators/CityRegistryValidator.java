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
            System.out.println("Отец: " + hans);
            CityRegisterCheckerResponse wans = personChecker.checkPerson(sr.getWife());
            System.out.println("Мать: " + wans);
            for(Child child: sr.getChildren()) {
                CityRegisterCheckerResponse cans = personChecker.checkPerson(child);
                System.out.println("Ребенок: " + cans);
            }
        } catch (CityRegisterException e) {
            e.printStackTrace();
        }
        return new AnswerCityRegistry();
    }
}
