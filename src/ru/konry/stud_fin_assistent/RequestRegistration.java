package ru.konry.stud_fin_assistent;

import ru.konry.stud_fin_assistent.domains.Adult;
import ru.konry.stud_fin_assistent.domains.Person;
import ru.konry.stud_fin_assistent.domains.StudentRequest;

public class RequestRegistration {
    public static void main(String[] args) {

        StudentRequest sr = createStudentRequest();

        long id = registryRequest(sr);
        System.out.println("Заявка зарегистрирована. Номер заявки " +
                id + " Студент " + sr.getHusband().getPersonString());
    }

    static long registryRequest(StudentRequest sr) {
        long answer = 42;
        System.out.println("Регистрация заявки студента... "
                + sr.getHusband().getName()  + " " + sr.getHusband().getSurname());
        return answer;
    }

    static StudentRequest createStudentRequest() {
        StudentRequest sr = new StudentRequest();
        Adult husband = new Adult();
        husband.setName("Константин");
        husband.setSurname("Корчагин");
        husband.setPassportNumber(123456);
        sr.setHusband(husband);
        return  sr;
    }
}
