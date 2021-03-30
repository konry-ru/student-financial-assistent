package ru.konry.stud_fin_assistent.validators;

import ru.konry.stud_fin_assistent.answers.AnswerIsMarried;
import ru.konry.stud_fin_assistent.domains.StudentRequest;

public class MarriageValidator {

    public AnswerIsMarried checkIsMarried(StudentRequest sr) {
        System.out.println("Проверка семейного положения...");
        return new AnswerIsMarried();
    }
}
