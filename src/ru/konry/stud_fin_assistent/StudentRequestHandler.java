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

    StudentRequest stRequest;
    CityRegistryValidator crValidator;
    StudentListValidator slValidator;
    MarriageValidator mrValidator;
    ChildrenValidator chValidator;
    MailSender mlSender;

    public StudentRequestHandler() {
        stRequest = new StudentRequest();
        crValidator = new CityRegistryValidator();
        slValidator = new StudentListValidator();
        mrValidator = new MarriageValidator();
        chValidator = new ChildrenValidator();
        mlSender = new MailSender();
    }

    public static void main(String[] args) {
        StudentRequestHandler srHandler = new StudentRequestHandler();
        srHandler.checkAllValidations();
    }

    public void checkAllValidations() {

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

    public StudentRequest readStudentRequest() {
        System.out.println("Получение студенческой заявки из хранилища...");
        return stRequest;
    }

    public AnswerCityRegistry checkCityRegistry(StudentRequest sr) {
        crValidator.hostName = "Host_1";
        crValidator.login = "Login_1";
        AnswerCityRegistry ansCR = crValidator.checkCityRegistry(sr);
        return ansCR;
    }

    public AnswerIsStudent checkStudentsList(StudentRequest sr) {
        slValidator.hostName = "Host_for_check_is_student";
        return slValidator.checkStList(sr);
    }

    public AnswerIsMarried checkIsMarried(StudentRequest sr) {
        return mrValidator.checkIsMarried(sr);
    }

    public AnswerHasChildren checkChildren(StudentRequest sr) {
        return chValidator.checkChildren(sr);
    }

    public void sendMail() {
        mlSender.sendMail();
    }
}
