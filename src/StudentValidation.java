public class StudentValidation {
    public static void main(String[] args) {
        checkAllValidations();
    }

    static void checkAllValidations() {
        StudentRequest sr = readStudentRequest();

        AnswerCityRegistry answerCityRegistry = checkCityRegistryDepartment(sr);
        AnswerIsStudent answerIsStudent = checkStudentsList(sr);
        AnswerIsMarriage answerIsMarriage = checkIsMarriage(sr);
        AnswerHasChildren answerHasChildren = checkChildren(sr);

        sendMail();
    }

    static StudentRequest readStudentRequest() {
        System.out.println("Получение студенческой заявки из хранилища...");
        return new StudentRequest();
    }

    static AnswerCityRegistry checkCityRegistryDepartment(StudentRequest sr) {
        System.out.println("Проверка прописки студента подавшего заявление...");
        return new AnswerCityRegistry();
    }

    static AnswerIsStudent checkStudentsList(StudentRequest sr) {
        System.out.println("Проверка является ли заявитель студентом...");
        return new AnswerIsStudent();
    }

    static AnswerIsMarriage checkIsMarriage(StudentRequest sr) {
        System.out.println("Проверка семейного положения...");
        return new AnswerIsMarriage();
    }

    static AnswerHasChildren checkChildren(StudentRequest sr) {
        System.out.println("Проверка сведений о детях через ЗАГС...");
        return new AnswerHasChildren();
    }

    static void sendMail() {
        System.out.println("Отправка сообщения о успешной или неуспешной регистрации завки...");
    }
}
