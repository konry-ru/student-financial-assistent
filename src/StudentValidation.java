public class StudentValidation {
    public static void main(String[] args) {
        checkAllValidations();
    }

    static void checkAllValidations() {

        while(true) {
            StudentRequest sr = readStudentRequest();
            System.out.println("Start");

            if(sr == null) break;
            System.out.println("After break");

            AnswerCityRegistry answerCityRegistry = checkCityRegistryDepartment(sr);
            if(!answerCityRegistry.success) continue;

            AnswerIsStudent answerIsStudent = checkStudentsList(sr);
            AnswerIsMarriage answerIsMarriage = checkIsMarriage(sr);
            AnswerHasChildren answerHasChildren = checkChildren(sr);

            sendMail();
        }
        System.out.println("Finish");
    }

    static StudentRequest readStudentRequest() {
        System.out.println("Получение студенческой заявки из хранилища...");
        StudentRequest studentRequest = new StudentRequest();
        return studentRequest;
    }

    static AnswerCityRegistry checkCityRegistryDepartment(StudentRequest sr) {
        System.out.println("Проверка прописки студента подавшего заявление...");
        AnswerCityRegistry answerCityRegistry = new AnswerCityRegistry();
        answerCityRegistry.success = false;
        return answerCityRegistry;
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
