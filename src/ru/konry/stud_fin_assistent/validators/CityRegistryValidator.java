package ru.konry.stud_fin_assistent.validators;

import ru.konry.stud_fin_assistent.domains.*;
import ru.konry.stud_fin_assistent.domains.registry.AnswerCityRegistryItem;
import ru.konry.stud_fin_assistent.exceptions.CityRegisterException;
import ru.konry.stud_fin_assistent.domains.registry.AnswerCityRegistry;
import ru.konry.stud_fin_assistent.domains.registry.CityRegisterResponse;

import java.util.List;

public class CityRegistryValidator {
    public String hostName;
    public String login;
    private String password = "1234";

    private final CityRegisterChecker personChecker;

    public CityRegistryValidator() {
        this.personChecker = new FakeCityRegisterChecker();
    }

    public AnswerCityRegistry checkCityRegistry(StudentRequest sr) {
        AnswerCityRegistry ans = new AnswerCityRegistry();
            ans.addItem(checkPerson(sr.getHusband()));
            ans.addItem(checkPerson(sr.getWife()));

            List<Child> children = sr.getChildren();
            for(Child child: children) {
                ans.addItem(checkPerson(child));
            }
        return ans;
    }

    private AnswerCityRegistryItem checkPerson(Person person) {
        try {
            CityRegisterResponse ans = personChecker.checkPerson(person);
        } catch (CityRegisterException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
}
