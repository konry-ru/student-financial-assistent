package ru.konry.stud_fin_assistent.validators;

import ru.konry.stud_fin_assistent.domains.student.AnswerIsStudent;
import ru.konry.stud_fin_assistent.domains.StudentRequest;

public class StudentListValidator {
    public String hostName;
    private String login = "Validator_1";
    private String password = "1234:)";

    public AnswerIsStudent checkStList(StudentRequest sr) {
        AnswerIsStudent answ = new AnswerIsStudent();
        answ.success = true;
        return answ;
    }
}
