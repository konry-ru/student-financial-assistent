public class StudentListValidator {
    String hostName;
    String login;
    String password;

    AnswerIsStudent checkStList(StudentRequest sr) {
        System.out.println("Проверка является ли заявитель студентом на хосте: " +
                hostName + " логин и пароль: " + login + " " + password);
        AnswerIsStudent answ = new AnswerIsStudent();
        answ.success = true;
        return answ;
    }
}
