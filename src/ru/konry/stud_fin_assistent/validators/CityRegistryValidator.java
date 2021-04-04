package ru.konry.stud_fin_assistent.validators;

import ru.konry.stud_fin_assistent.domains.*;

public class CityRegistryValidator {
    public String hostName;
    public String login;
    private String password = "1234";

    private CityRegisterChecker personChecker;

    public CityRegistryValidator() {
        this.personChecker = new FakeCityRegisterChecker();
    }

    public AnswerCityRegistry checkCityRegistry(StudentRequest sr) {

        CityRegisterCheckerResponse crcr = personChecker.checkPerson(sr.getHusband());

        AnswerCityRegistry ansCR = new AnswerCityRegistry();
        if(crcr.isExisting() == true) {
            System.out.println("Проверка выполнена успешно в городском реестре населения...");
            ansCR.success = true;
        } else {
            ansCR.success = false;
        }
        return ansCR;
    }
}
