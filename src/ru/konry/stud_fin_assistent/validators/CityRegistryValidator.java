package ru.konry.stud_fin_assistent.validators;

import ru.konry.stud_fin_assistent.domains.*;

public class CityRegistryValidator {
    public String hostName;
    public String login;
    private String password = "1234";

    public AnswerCityRegistry checkCityRegistry(StudentRequest sr) {
        System.out.println("Проверка прописки студента подавшего заявление..." +
                hostName + ", " + login + ", " + password);
        AnswerCityRegistry ansCR = new AnswerCityRegistry();
        ansCR.success = true;
        return ansCR;
    }
}
