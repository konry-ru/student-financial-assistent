package ru.konry.stud_fin_assistent.validators;

import ru.konry.stud_fin_assistent.domains.*;
import ru.konry.stud_fin_assistent.exceptions.CityRegisterException;

public class FakeCityRegisterChecker implements CityRegisterChecker {
    private static final String GOOD_1 = "1000";
    private static final String GOOD_2 = "2000";
    private static final String BAD_1 = "1001";
    private static final String BAD_2 = "2001";
    private static final String ERROR_1 = "1002";
    private static final String ERROR_2 = "2002";

    public CityRegisterCheckerResponse checkPerson(Person person) throws CityRegisterException {

        CityRegisterCheckerResponse res = new CityRegisterCheckerResponse();

        if(person instanceof Adult) {
            Adult p = (Adult) person;
            String ps = p.getPassportSeries();
            System.out.println(ps);
            if(ps.equals(GOOD_1) || ps.equals(GOOD_2)) {
                res.setExisting(true);
                res.setTemporal(false);
            }
            if(ps.equals(BAD_1) || ps.equals(BAD_2)) {
                res.setExisting(false);
            }
            if(ps.equals(ERROR_1) || ps.equals(ERROR_2)) {
                CityRegisterException ex = new CityRegisterException("Fake ERROR :)");
                throw ex;
            }
        }
        if(person instanceof Child) {
            res.setExisting(true);
            res.setTemporal(true);
        }
        System.out.println(res);
        return res;
    }
}
