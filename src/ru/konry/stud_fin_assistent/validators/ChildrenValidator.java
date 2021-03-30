package ru.konry.stud_fin_assistent.validators;

import ru.konry.stud_fin_assistent.answers.AnswerHasChildren;
import ru.konry.stud_fin_assistent.domains.StudentRequest;

public class ChildrenValidator {

     public AnswerHasChildren checkChildren(StudentRequest sr) {
        System.out.println("Проверка сведений о детях через ЗАГС...");
        return new AnswerHasChildren();
    }
}
