package ru.konry.stud_fin_assistent.validators;

import ru.konry.stud_fin_assistent.domains.*;
import ru.konry.stud_fin_assistent.exceptions.CityRegisterException;
import ru.konry.stud_fin_assistent.domains.registry.CityRegisterResponse;
import ru.konry.stud_fin_assistent.exceptions.TransportException;

public class FakeCityRegisterChecker implements CityRegisterChecker {
    private static final String GOOD_1 = "1000";
    private static final String GOOD_2 = "2000";
    private static final String BAD_1 = "1001";
    private static final String BAD_2 = "2001";
    private static final String ERROR_1 = "1002";
    private static final String ERROR_2 = "2002";

    private static final String REG_ERROR_MESSAGE = "Fake CityRegistry ERROR :)";
    private static final String REG_ERROR_CODE = "555";
    private static final String TRANS_ERROR_MESSAGE = "DISCONNECT";

    public CityRegisterResponse checkPerson(Person person) throws CityRegisterException, TransportException
    {
        CityRegisterResponse res = new CityRegisterResponse();

        if(person instanceof Adult) {
            Adult p = (Adult) person;
            String ps = p.getPassportSeries();
            if(ps.equals(GOOD_1) || ps.equals(GOOD_2)) {
                res.setExisting(true);
                res.setTemporal(false);
            }
            if(ps.equals(BAD_1) || ps.equals(BAD_2)) {
                CityRegisterException ex = new CityRegisterException(REG_ERROR_MESSAGE, REG_ERROR_CODE);
                throw ex;
            }
            if(ps.equals(ERROR_1) || ps.equals(ERROR_2)) {
                TransportException tex = new TransportException(TRANS_ERROR_MESSAGE);
                throw tex;
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
