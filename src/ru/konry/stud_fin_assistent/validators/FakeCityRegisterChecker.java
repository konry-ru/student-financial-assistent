package ru.konry.stud_fin_assistent.validators;

import ru.konry.stud_fin_assistent.domains.CityRegisterCheckerResponse;
import ru.konry.stud_fin_assistent.domains.Person;

public class FakeCityRegisterChecker implements CityRegisterChecker {
    public CityRegisterCheckerResponse checkPerson(Person person) {
        CityRegisterCheckerResponse ans = new CityRegisterCheckerResponse();
        ans.setExisting(true);
        return ans;
    }
}
