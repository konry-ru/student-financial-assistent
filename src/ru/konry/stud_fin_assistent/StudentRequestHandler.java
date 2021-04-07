package ru.konry.stud_fin_assistent;

import ru.konry.stud_fin_assistent.domains.*;
import ru.konry.stud_fin_assistent.mail.*;
import ru.konry.stud_fin_assistent.validators.*;

public class StudentRequestHandler {

    CityRegistryValidator crValidator;
    StudentListValidator slValidator;
    MarriageValidator mrValidator;
    ChildrenValidator chValidator;
    MailSender mlSender;

    public StudentRequestHandler() {
        crValidator = new CityRegistryValidator();
        slValidator = new StudentListValidator();
        mrValidator = new MarriageValidator();
        chValidator = new ChildrenValidator();
        mlSender = new MailSender();
    }

    public StudentRequest[] readStudentRequests() {
        StudentRequest[] stRequestsArray = new StudentRequest[1];
        for(int c = 0; c < stRequestsArray.length; c++) {
            stRequestsArray[c] = RequestRegistration.createStudentRequest(c);
        }
        return stRequestsArray;
    }

    public void checkAllValidations() {

        StudentRequest[] stRequests = readStudentRequests();
        for (StudentRequest stRequest : stRequests) {
            checkOneStudentRequest(stRequest);
        }
    }

    public void checkOneStudentRequest(StudentRequest sr) {
        AnswerCityRegistry answerCityRegistry = checkCityRegistry(sr);
//        AnswerIsStudent answerIsStudent = checkStudentsList(sr);
//        AnswerIsMarried answerIsMarried = checkIsMarried(sr);
//        AnswerHasChildren answerHasChildren = checkChildren(sr);
//        sendMail();
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

    public static void main(String[] args) {
        StudentRequestHandler srHandler = new StudentRequestHandler();
        srHandler.checkAllValidations();
    }
}
