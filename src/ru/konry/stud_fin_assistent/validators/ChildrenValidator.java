package ru.konry.stud_fin_assistent.validators;

import ru.konry.stud_fin_assistent.domains.children.AnswerHasChildren;
import ru.konry.stud_fin_assistent.domains.StudentRequest;

public class ChildrenValidator {

     public AnswerHasChildren checkChildren(StudentRequest sr) {
        return new AnswerHasChildren();
    }
}
