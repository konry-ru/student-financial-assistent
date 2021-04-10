package ru.konry.stud_fin_assistent;

import ru.konry.stud_fin_assistent.domains.*;
import ru.konry.stud_fin_assistent.domains.children.AnswerHasChildren;
import ru.konry.stud_fin_assistent.domains.registry.AnswerCityRegistryItem;
import ru.konry.stud_fin_assistent.domains.student.AnswerIsStudent;
import ru.konry.stud_fin_assistent.domains.wedding.AnswerIsMarried;
import ru.konry.stud_fin_assistent.mail.*;
import ru.konry.stud_fin_assistent.domains.registry.AnswerCityRegistry;
import ru.konry.stud_fin_assistent.validators.*;

import java.util.LinkedList;

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

    public LinkedList<StudentRequest> readStudentRequests() {
        LinkedList<StudentRequest> stRequestsList = new LinkedList<StudentRequest>();
        for(int c = 0; c < 1; c++) {
            StudentRequest newRequest = RequestRegistration.createStudentRequest(c);
            stRequestsList.add(newRequest);
        }
        return stRequestsList;
    }

    public LinkedList<AnswerCityRegistry> checkAllValidations() {

        LinkedList<StudentRequest> stRequests = readStudentRequests();
        LinkedList<AnswerCityRegistry> answers = new LinkedList<>();
        for (StudentRequest stRequest : stRequests) {
            AnswerCityRegistry ans = checkOneStudentRequest(stRequest);
            answers.add(ans);
        }
        return answers;
    }

    public AnswerCityRegistry checkOneStudentRequest(StudentRequest sr) {
        AnswerCityRegistry answerCityRegistry = checkCityRegistry(sr);
//        AnswerIsStudent answerIsStudent = checkStudentsList(sr);
//        AnswerIsMarried answerIsMarried = checkIsMarried(sr);
//        AnswerHasChildren answerHasChildren = checkChildren(sr);
//        sendMail();
        return answerCityRegistry;
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
        LinkedList<AnswerCityRegistry> answers = srHandler.checkAllValidations();
        for(AnswerCityRegistry ans : answers) {
            for(AnswerCityRegistryItem item : ans.getItems()) {
                System.out.println(item);
            }
        }
    }
}
