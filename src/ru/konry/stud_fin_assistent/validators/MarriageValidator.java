package ru.konry.stud_fin_assistent.validators;

import ru.konry.stud_fin_assistent.domains.AnswerIsMarried;
import ru.konry.stud_fin_assistent.domains.StudentRequest;

public class MarriageValidator {

    public AnswerIsMarried checkIsMarried(StudentRequest sr) {
        return new AnswerIsMarried();
    }
}
