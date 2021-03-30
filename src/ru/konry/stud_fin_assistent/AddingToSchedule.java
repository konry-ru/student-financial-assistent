package ru.konry.stud_fin_assistent;

public class AddingToSchedule {
    public static void main(String[] args) {
        setSchedule();
    }

    static void setSchedule() {
        getFreeTimeList();
        selectVisitTime();
        saveVisitTime();
    }

    static void getFreeTimeList() {
        System.out.println("Получение списка свободных часов посещения...");
    }

    static void selectVisitTime() {
        System.out.println("Выбор студентом времени посещения из спска...");
    }

    static void saveVisitTime() {
        System.out.println("Сохранение времени посещения...");
    }
}
