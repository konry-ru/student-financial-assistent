public class MarriageValidator {

    AnswerIsMarried checkIsMarried(StudentRequest sr) {
        System.out.println("Проверка семейного положения...");
        return new AnswerIsMarried();
    }
}
