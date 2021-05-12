package ru.konry.stud_fin_assistent.domains;

public enum StudentRequestStatus
{
    START, CREATED;

    public static StudentRequestStatus statusFromValue(int value) {
        for (StudentRequestStatus status : StudentRequestStatus.values()) {
            if (status.ordinal() == value) {
                return status;
            }
        }
        throw new RuntimeException("Недопустимый статус заявки: " + value);
    }
}
