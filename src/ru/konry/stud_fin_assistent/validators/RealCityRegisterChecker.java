package ru.konry.stud_fin_assistent.validators;

import ru.konry.stud_fin_assistent.domains.registry.CityRegisterResponse;
import ru.konry.stud_fin_assistent.domains.Person;
import ru.konry.stud_fin_assistent.exceptions.CityRegisterException;
import ru.konry.stud_fin_assistent.exceptions.TransportException;

public class RealCityRegisterChecker implements CityRegisterChecker
{
    public CityRegisterResponse checkPerson(Person person) throws CityRegisterException, TransportException
    {
        CityRegisterResponse res = new CityRegisterResponse();
        res.setExisting(true);
        return res;
    }
}
