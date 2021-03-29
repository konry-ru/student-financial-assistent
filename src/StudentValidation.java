public class StudentValidation {
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

    static AnswerCityRegistry checkCityRegistry(StudentRequest sr) {
        CityRegistryValidator crv = new CityRegistryValidator();
        crv.hostName = "Host_1";
        crv.login = "Login_1";
        crv.password = "Password_1";
        AnswerCityRegistry ansCR = crv.checkCityRegistry(sr);
        return ansCR;
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
