public class CityRegistryValidator {
    String hostName;
    String login;
    String password;

    AnswerCityRegistry checkCityRegistry(StudentRequest sr) {
        System.out.println("Проверка прописки студента подавшего заявление..." +
                hostName + ", " + login + ", " + password);
        AnswerCityRegistry ansCR = new AnswerCityRegistry();
        ansCR.success = false;
        return ansCR;
    }
}
