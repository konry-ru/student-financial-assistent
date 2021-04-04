package ru.konry.stud_fin_assistent.validators;

import ru.konry.stud_fin_assistent.domains.CityRegisterCheckerResponse;
import ru.konry.stud_fin_assistent.domains.Person;
import ru.konry.stud_fin_assistent.exceptions.CityRegisterException;

public class FakeCityRegisterChecker implements CityRegisterChecker {
    public CityRegisterCheckerResponse checkPerson(Person person) throws CityRegisterException {
//        throw new CityRegisterException();
        CityRegisterCheckerResponse ans = new CityRegisterCheckerResponse();
        ans.setExisting(true);
        return ans;
    }
}
