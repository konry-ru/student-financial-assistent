package ru.konry.stud_fin_assistent;

import ru.konry.stud_fin_assistent.domains.AnswerCityRegistry;
import ru.konry.stud_fin_assistent.domains.AnswerHasChildren;
import ru.konry.stud_fin_assistent.domains.AnswerIsMarried;
import ru.konry.stud_fin_assistent.domains.AnswerIsStudent;
import ru.konry.stud_fin_assistent.domains.StudentRequest;
import ru.konry.stud_fin_assistent.mail.MailSender;
import ru.konry.stud_fin_assistent.validators.ChildrenValidator;
import ru.konry.stud_fin_assistent.validators.CityRegistryValidator;
import ru.konry.stud_fin_assistent.validators.MarriageValidator;
import ru.konry.stud_fin_assistent.validators.StudentListValidator;

public class StudentRequestHandler {

    public static void main(String[] args) {
        checkAllValidations();
    }

    static void checkAllValidations() {

        while(true) {
            StudentRequest sr = readStudentRequest();

            if(sr == null) break;

            AnswerCityRegistry answerCityRegistry = checkCityRegistry(sr);
            if(!answerCityRegistry.success) break;

            AnswerIsStudent answerIsStudent = checkStudentsList(sr);
            if(!answerIsStudent.success) break;

            AnswerIsMarried answerIsMarried = checkIsMarried(sr);
            AnswerHasChildren answerHasChildren = checkChildren(sr);

            sendMail();
            break;
        }
    }

    static StudentRequest readStudentRequest() {
        System.out.println("Получение студенческой заявки из хранилища...");
        StudentRequest studentRequest = new StudentRequest();
        return studentRequest;
    }

    static AnswerCityRegistry checkCityRegistry(StudentRequest sr) {
        CityRegistryValidator crv = new CityRegistryValidator();
        crv.hostName = "Host_1";
        crv.login = "Login_1";
        AnswerCityRegistry ansCR = crv.checkCityRegistry(sr);
        return ansCR;
    }

    static AnswerIsStudent checkStudentsList(StudentRequest sr) {
        StudentListValidator stListValid = new StudentListValidator();
        stListValid.hostName = "Host_for_check_is_student";
        return stListValid.checkStList(sr);
    }

    static AnswerIsMarried checkIsMarried(StudentRequest sr) {

        MarriageValidator marValid = new MarriageValidator();
        return marValid.checkIsMarried(sr);
    }

    static AnswerHasChildren checkChildren(StudentRequest sr) {

        return new ChildrenValidator().checkChildren(sr);
    }

    static void sendMail() {
        new MailSender().sendMail();
    }
}
