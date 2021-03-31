package ru.konry.stud_fin_assistent.validators;

import ru.konry.stud_fin_assistent.domains.AnswerIsStudent;
import ru.konry.stud_fin_assistent.domains.StudentRequest;

public class StudentListValidator {
    public String hostName;
    private String login = "Validator_1";
    private String password = "1234:)";

    public AnswerIsStudent checkStList(StudentRequest sr) {
        System.out.println("Проверка является ли заявитель студентом на хосте: " +
                hostName + " логин и пароль: " + login + " " + password);
        AnswerIsStudent answ = new AnswerIsStudent();
        answ.success = true;
        return answ;
    }
}
