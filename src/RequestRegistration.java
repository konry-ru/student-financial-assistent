public class RequestRegistration {
    public static void main(String[] args) {
        StudentRequest sr1;
        sr1 = new StudentRequest();
        sr1.hFirstName = "Константин";
        sr1.hLastName = "Корчагин";
        sr1.wFirstName = "Анна";
        sr1.wLastName = "Корчажкина";
        long id = registryRequest(sr1);
        System.out.println("Заявка зарегистрирована. Номер заявки " + id);

        StudentRequest sr2;
        sr2 = new StudentRequest();
        sr2.hFirstName = "Иван";
        sr2.hLastName = "Сусанин";
        sr2.wFirstName = "Елена";
        sr2.wLastName = "Сусанина";
        id = registryRequest(sr2);
        System.out.println("Заявка зарегистрирована. Номер заявки " + id);
    }

    static long registryRequest(StudentRequest studentRequest) {
        long answer = 42;
        System.out.println("Регистрация заявки студента... " + studentRequest.hFirstName + " " + studentRequest.hLastName);
        return answer;
    }
}
