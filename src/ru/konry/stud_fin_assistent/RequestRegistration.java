package ru.konry.stud_fin_assistent;

import ru.konry.stud_fin_assistent.domains.StudentRequest;

public class RequestRegistration {
    public static void main(String[] args) {
        StudentRequest sr1;
        sr1 = new StudentRequest();
        sr1.sethFirstName("Константин");
        sr1.sethLastName("Корчагин");
        sr1.setwFirstName("Анна");
        sr1.setwLastName("Корчажкина");
        long id = registryRequest(sr1);
        System.out.println("Заявка зарегистрирована. Номер заявки " + id);
    }

    static long registryRequest(StudentRequest sr) {
        long answer = 42;
        System.out.println("Регистрация заявки студента... " + sr.gethFirstName() + " " + sr.gethLastName());
        return answer;
    }
}
