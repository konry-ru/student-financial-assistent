package ru.konry.stud_fin_assistent.validators;

import ru.konry.stud_fin_assistent.domains.CityRegisterCheckerResponse;
import ru.konry.stud_fin_assistent.domains.Person;

public interface CityRegisterChecker {
    CityRegisterCheckerResponse checkPerson(Person p);
}
