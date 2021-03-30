public class ChildrenValidator {

     AnswerHasChildren checkChildren(StudentRequest sr) {
        System.out.println("Проверка сведений о детях через ЗАГС...");
        return new AnswerHasChildren();
    }
}
