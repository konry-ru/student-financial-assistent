package ru.konry.stud_fin_assistent.validators;

import ru.konry.stud_fin_assistent.domains.registry.CityRegisterResponse;
import ru.konry.stud_fin_assistent.domains.Person;
import ru.konry.stud_fin_assistent.exceptions.CityRegisterException;
import ru.konry.stud_fin_assistent.exceptions.TransportException;

public interface CityRegisterChecker {
    CityRegisterResponse checkPerson(Person p) throws CityRegisterException, TransportException;
}
