package ru.konry.stud_fin_assistent;

import ru.konry.stud_fin_assistent.domains.*;

public class RequestRegistration {

    static StudentRequest createStudentRequest(long id) {
        StudentRequest sr = new StudentRequest();
        sr.setStudentRequestId(id);
        StudentRequest sr1 = sr;
        printStR(sr1);
        System.out.println("Создана заявка id: " + id);
        return  sr1;
    }

    static void saveStudentRequest(StudentRequest sr) {
        System.out.println("Сохранение заявки студента... "
                + sr.getHusband().getName()  + " " + sr.getHusband().getSurname());
    }

    static void printStR(StudentRequest sreq) {
        System.out.println("Print request..." + sreq);
    }

    public static void main(String[] args) {

        StudentRequest sr = createStudentRequest(42);

        saveStudentRequest(sr);
        System.out.println("Заявка зарегистрирована. Номер заявки " +
                sr.getStudentRequestId() + " Студент " + sr.getHusband().getName() + " " + sr.getHusband().getSurname());
    }
}
