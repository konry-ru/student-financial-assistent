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
            if(!answerIsStudent.success) break;

            AnswerIsMarried answerIsMarried = checkIsMarried(sr);
            AnswerHasChildren answerHasChildren = checkChildren(sr);

            sendMail();
            break;
        }
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
        StudentListValidator stListValid = new StudentListValidator();
        stListValid.hostName = "Host_for_check_is_student";
        stListValid.login = "Validator_1";
        stListValid.password = "1234:)";
        return stListValid.checkStList(sr);
    }

    static AnswerIsMarried checkIsMarried(StudentRequest sr) {

        MarriageValidator marValid = new MarriageValidator();
        return marValid.checkIsMarried(sr);
    }

    static AnswerHasChildren checkChildren(StudentRequest sr) {

        return new ChildrenValidator().checkChildren(sr);
    }

    static void sendMail() {
        new MailSender().sendMail();
    }
}
