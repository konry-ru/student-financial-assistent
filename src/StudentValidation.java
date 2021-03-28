public class StudentValidation {
    public static void main(String[] args) {
        checkAllValidations();
    }

    static void checkAllValidations() {
        checkCityRegistryDepartment();
        checkStudentsList();
        checkIsMarrige();
        checkChildren();
    }

    static void checkCityRegistryDepartment() {
        System.out.println("Проверка прописки студента подавшего заявление...");
    }

    static void checkStudentsList() {
        System.out.println("Проверка является ли заявитель студентом...");
    }

    static void checkIsMarrige() {
        System.out.println("Проверка семейного положения...");
    }

    static void checkChildren() {
        System.out.println("Проверка сведений о детях через ЗАГС...");
    }
}
