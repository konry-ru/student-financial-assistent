package ru.konry.stud_fin_assistent.validators;

import ru.konry.stud_fin_assistent.domains.CityRegisterCheckerResponse;
import ru.konry.stud_fin_assistent.domains.Person;

public class RealCityRegisterChecker implements CityRegisterChecker {
    public CityRegisterCheckerResponse checkPerson(Person person) {
        CityRegisterCheckerResponse res = new CityRegisterCheckerResponse();
        res.setExisting(true);
        return res;
    }
}
