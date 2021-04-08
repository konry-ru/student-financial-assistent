package ru.konry.stud_fin_assistent.validators;

import ru.konry.stud_fin_assistent.domains.registry.CityRegisterCheckerResponse;
import ru.konry.stud_fin_assistent.domains.Person;
import ru.konry.stud_fin_assistent.exceptions.CityRegisterException;

public class RealCityRegisterChecker implements CityRegisterChecker {
    public CityRegisterCheckerResponse checkPerson(Person person) throws CityRegisterException {
        CityRegisterCheckerResponse res = new CityRegisterCheckerResponse();
        res.setExisting(true);
        return res;
    }
}
